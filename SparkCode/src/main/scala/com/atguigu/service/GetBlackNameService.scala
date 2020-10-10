package com.atguigu.service

import com.atguigu.dao.GetBlackNameDao
import com.atguigu.util.{DataToBeanUtil, GetDStreamFromKafkaUtil}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges}
import summerframework.code.TService
import summerframework.util.{EnvUtils, JDBCUtil}

import scala.collection.mutable.ListBuffer

/**
 * 实现实时的动态黑名单机制：将每天对某个广告点击超过 100 次的用户拉黑。
 * 注：黑名单保存到MySQL中。
 * (1)(某用户-每天-某个广告)--->次数
 * (2)实时累加数据 把累加的结果插入更新到中间表中 历史记录存在mysql中
 * (3)从用户表查询出记录超过100的用户
 * (4)超过100 插入黑名单中
 * 总结:
 *    (1)把offset提交给kafka中的时候 在设置rdd.asInstanceOf[HasOffsetRanges].offsetRanges之前
 *    不能对rdd进行任何的操作
 *    (2)asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges) 不能放入到foreachPartition中
 *     因为foreachPartition中的代码是以job的形式 提交给executor中运行的
 */
class GetBlackNameService extends TService{
  private val dao = new GetBlackNameDao
  override def analysis(): Unit = {
    val kafkaData =dao.getKafkaData
    //(1)(某用户-每天-某个广告)--->次数
    //val countDS = kafkaData.map(beans => ((beans.userId, beans.dayString, beans.adsId), 1))
      //.reduceByKey(_ + _)
    // (2)实时累加数据 把累加的结果插入更新到中间表中 历史记录存在mysql中
    kafkaData.foreachRDD(rdd=>{
      // 把offset提交给kafka维护
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      // 处理每个rdd
      val resultRDD = DataToBeanUtil.getAllBeans(rdd)
        .map(beans => ((beans.userId, beans.dayString, beans.adsId), 1))
        .reduceByKey(_+_)
      resultRDD.foreachPartition(iter=>{
        val connection = JDBCUtil.getConnection()
        // 此sql表示 如果没有记录就插入,有记录就更新
        val ps1 = connection.prepareStatement(
          """
            |INSERT INTO user_ad_count VALUES(?,?,?,?)
            |ON DUPLICATE KEY UPDATE COUNT=COUNT+?
            |""".stripMargin)
        // 按每个分区的条记录都进行操作
        iter.foreach{
          case ((userId, dayString, adsId),count)=>
            ps1.setString(1,dayString)
            ps1.setString(2,userId)
            ps1.setString(3,adsId)
            ps1.setInt(4,count)
            ps1.setInt(5,count)
            ps1.executeUpdate()
        }
        // 释放资源
        ps1.close()
        // 从用户表查询出记录超过100的用户
        val ps2 = connection.prepareStatement(
          """
            |select userid from user_ad_count where count > 20
            |""".stripMargin)
        val resultSet = ps2.executeQuery()
        // 把记录存入集合中 用于黑名单表的插入
        val listBuffer = new ListBuffer[String]()
        while (resultSet.next()){
          listBuffer.append(resultSet.getString("userid"))
        }
        // 释放资源
        resultSet.close()
        ps2.close()

        // 把超过100 插入黑名单中
        val ps3 = connection.prepareStatement(
          """
            |INSERT INTO black_list VALUES(?)
            | ON DUPLICATE KEY UPDATE userid=?
            |""".stripMargin)
       // 对每一条黑名单进行插入
        listBuffer.foreach(name=>{
          ps3.setString(1,name)
          ps3.setString(2,name)
          ps3.executeUpdate()
        })
        ps3.close()
        connection.close()
      })
      // 提交成功后提交offset
      kafkaData.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    })
  }

  override def analysis(data: Any): Any = ???
}
