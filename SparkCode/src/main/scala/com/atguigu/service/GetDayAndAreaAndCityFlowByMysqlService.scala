package com.atguigu.service

import com.atguigu.dao.GetBlackNameDao
import com.atguigu.util.DataToBeanUtil
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges}
import summerframework.code.TService
import summerframework.util.{EnvUtils, JDBCUtil}

import scala.collection.mutable.ListBuffer

/**
 * 实时统计每天各地区各城市各广告的点击总流量，并将其存入MySQL
 * (1)统计每天-每个地区-每个城市-广告===>sum
 * (2)因为动态地统计每天各地区各城市各广告的点击总流量 所以需要把当前的广告点击量和当日内的所有的广告点击量和并(需要使用外部存储)
 * (3)把和合并后的数据写入mysql中
 *
 *
 * 问题： 使用ck目录保存每个采集周期计算的结果的状态！会造成小文件！
 * 解决：  ① 在集群中部署，ck目录在hdfs。 可以先一个程序，程序监控ck目录，将已经过期的ck目录删除！
 * 程序-----> 每间隔5分钟提交一个Job
 * 监控程序----->删除当前时间前15分钟之前所有生成的ck目录
 * ② 从根本上解决，不使用ck目录记录state，自然不能使用updateStageByKey算子
 * 怎么完成有状态的计算？  自己维护state(mysql,redis)!
 * a) 程序在计算当前 这批数据时，先从数据库中，查询state
 * state + 当前计算的结果  合并 =  新的state
 * b) 将state写入数据库
 */
class GetDayAndAreaAndCityFlowByMysqlService extends TService {
  private val dao = new GetBlackNameDao

  override def analysis(): Any = {
    // 获取kafka中的数据
    val kafkaData = dao.getKafkaData
    // 3)把和合并后的数据写入mysql中
    kafkaData.foreachRDD(rdd => {
      // 获取这个rdd的offset
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val beanRdd = DataToBeanUtil.getAllBeans(rdd)
      // (1) 统计每天各地区各城市各广告的点击总流量
      val resultRDD = beanRdd.map(beans => ((beans.dayString, beans.area, beans.city, beans.adsId), 1))
        .reduceByKey(_ + _)
      resultRDD.foreachPartition(iter => {
        val connection = JDBCUtil.getConnection()
        val ps1 = connection.prepareStatement(
          """
            |select
            |count
            |from area_city_ad_count
            |where dt=?
            |and area=?
            |and city=?
            |and adid=?
            |""".stripMargin)
        val ps = connection.prepareStatement(
          """
            |INSERT INTO  `area_city_ad_count` VALUES(?,?,?,?,?)
            |ON DUPLICATE KEY UPDATE COUNT=?
            |""".stripMargin)
        iter.foreach {
          case ((dayString, area, city, adsId), count) =>

            // 查出历史记录
            ps1.setString(1,dayString)
            ps1.setString(2,area)
            ps1.setString(3,city)
            ps1.setString(4,adsId)
            val resultSet = ps1.executeQuery()
            var sum:Int=count
            while (resultSet.next()){
              sum+=resultSet.getInt("count")
            }
            ps.setString(1, dayString)
            ps.setString(2, area)
            ps.setString(3, city)
            ps.setString(4, adsId)
            ps.setInt(5, sum)
            ps.setInt(6, sum)
            ps.executeUpdate()

        }
        ps.close()
        connection.close()
      })
      // 提交offset到kafka中
      kafkaData.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    })

  }

  override def analysis(data: Any): Any = ???
}
