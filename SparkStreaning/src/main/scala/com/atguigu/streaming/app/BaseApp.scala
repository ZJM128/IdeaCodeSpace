package com.atguigu.streaming.app

import com.atguigu.streaming.beans.AdsInfo
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

trait BaseApp {

   val streamingContext = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("my"), Seconds(3))

  def runAPP(op: =>Unit): Unit ={
    // 运行自定义逻辑
    op
    streamingContext.start()
    streamingContext.awaitTermination()
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
