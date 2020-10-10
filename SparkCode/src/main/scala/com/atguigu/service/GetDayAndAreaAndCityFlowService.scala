package com.atguigu.service

import com.atguigu.dao.GetBlackNameDao
import com.atguigu.util.DataToBeanUtil
import summerframework.code.TService
import summerframework.util.{EnvUtils, JDBCUtil}

/**
 * 实时统计每天各地区各城市各广告的点击总流量，并将其存入MySQL
 *    (1)统计每天-每个地区-每个城市-广告===>sum
 *    (2)因为动态地统计每天各地区各城市各广告的点击总流量 所以需要把当前的广告点击量和当日内的所有的广告点击量和并(需要使用外部存储)
 *    (3)把和合并后的数据写入mysql中
 *
 *
 *问题： 使用ck目录保存每个采集周期计算的结果的状态！会造成小文件！
 *           解决：  ① 在集群中部署，ck目录在hdfs。 可以先一个程序，程序监控ck目录，将已经过期的ck目录删除！
 *                         程序-----> 每间隔5分钟提交一个Job
 *                         监控程序----->删除当前时间前15分钟之前所有生成的ck目录
 *                  ② 从根本上解决，不使用ck目录记录state，自然不能使用updateStageByKey算子
 *                       怎么完成有状态的计算？  自己维护state(mysql,redis)!
 *                             a) 程序在计算当前 这批数据时，先从数据库中，查询state
 *                                   state + 当前计算的结果  合并 =  新的state
 *                             b) 将state写入数据库
 */
class GetDayAndAreaAndCityFlowService extends TService{
  private val dao = new GetBlackNameDao
  override def analysis(): Any = {
    // 获取kafka中的数据
    val kafkaData = dao.getKafkaData
    // (1)统计每天-每个地区-每个城市-广告===>sum
    val beanDS = DataToBeanUtil.getAllBeans(kafkaData)
    // 保存在CheckPoint中
    EnvUtils.getStreamingContext.checkpoint("GetDayAndAreaAndCityFlowService")
    //2)因为动态地统计每天各地区各城市各广告的点击总流量 所以需要把当前的广告点击量和当日内的所有的广告点击量和并(需要使用外部存储)
    val resultDS = beanDS.map(beans => ((beans.dayString, beans.area, beans.city, beans.adsId), 1))
    val value = resultDS.updateStateByKey((values: Seq[Int], sum: Option[Int]) => Some(values.sum + sum.getOrElse(0)))
    // 3)把和合并后的数据写入mysql中
    value.foreachRDD(rdd=>{
      rdd.foreachPartition(iter=>{
        val connection = JDBCUtil.getConnection()
        val ps = connection.prepareStatement(
          """
            |INSERT INTO  `area_city_ad_count` VALUES(?,?,?,?,?)
            |ON DUPLICATE KEY UPDATE COUNT=?
            |""".stripMargin)
        iter.foreach{
          case ((dayString, area, city, adsId),count)=>
            ps.setString(1,dayString)
            ps.setString(2,area)
            ps.setString(3,city)
            ps.setString(4,adsId)
            ps.setInt(5,count)
            ps.setInt(6,count)
            ps.executeUpdate()
        }
        ps.close()
        connection.close()
      })
    })

  }

  override def analysis(data: Any): Any = ???
}
