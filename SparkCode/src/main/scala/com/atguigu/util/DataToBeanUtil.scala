package com.atguigu.util

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import summerframework.bean.AdsInfo

object DataToBeanUtil {

  /**
   * 传入数据封装为Bean返回
   * @param [ConsumerRecord[String, String]]
   * @return RDD[AdsInfo]
   */
  def getAllBeans(ds:RDD[ConsumerRecord[String, String]]): RDD[AdsInfo] ={
    ds.map(record => {
      val arr = record.value().split(",")
      AdsInfo(
        arr(0).toLong,
        arr(1),
        arr(2),
        arr(3),
        arr(4)
      )
    })
  }

  def getAllBeans(ds:InputDStream[ConsumerRecord[String, String]]): DStream[AdsInfo] ={
    ds.map(record => {
      val arr = record.value().split(",")
      AdsInfo(
        arr(0).toLong,
        arr(1),
        arr(2),
        arr(3),
        arr(4)
      )
    })
  }
}
