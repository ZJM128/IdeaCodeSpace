package com.atguigu.day01

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, ConsumerStrategies, HasOffsetRanges, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 基于CheckPoint的问题 SparkKafka提供了吧offset维护到kafka中
 */
object $06_KafkaDirectModeManunal {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mySpark")

    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    val  kafkaParams= Map[String, String](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      "enable.auto.commit" -> "false",
      "auto.commit.interval.ms" -> "500",
      "group.id" -> "getKafka",
      "client.id" -> "s2",
      "auto.offset.reset" -> "earliest",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )

    //                                                                            key(默认是null)     value
    val ds = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](List("sparkstream"), kafkaParams)
    )

    ds.foreachRDD(rdd=>{
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges // 获取该信息的offset
      // 处理数据
    /*  val valueRDD = rdd.map(record => {
        if(record.value()=="d"){
          throw new RuntimeException("发生异常")
        }
        record.value()
      })
      println(valueRDD.collect().mkString(","))*/
    val rdd2 = rdd.map(x => x)
      rdd2.foreachPartition(iter=>{
        val iterStr = iter.map(record => {
          if (record.value() == "a") {
            throw new RuntimeException("发生异常")
          }
         record.value()
        })
        println(iterStr.mkString(","))
      })

      // 上面的业务逻辑没有出现异常就把offset维护到kafka中,如果出现异常就没有机会提交该offset
      ds.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    })

    // 启动应用 开始计算
    streamingContext.start()

    // 阻塞当前main线程
    streamingContext.awaitTermination()
  }
}
