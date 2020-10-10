package com.atguigu.streaming.utils

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

/**
 * 从Kafka中获取数据
 */
object GetDStreamFromKafkaUtil {

  def getDStreamFromKafka(context:StreamingContext): InputDStream[ConsumerRecord[String, String]] ={
    //准备map，其中保存消费者的参数  不记得，参考ConsumerConfig
    val kafkaParams: Map[String, String] = Map[String, String](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      "enable.auto.commit" -> "true",
      "auto.commit.interval.ms" -> "1000",
      "group.id" -> "zhijm",
      "client.id" -> "s1",
      "auto.offset.reset" -> "earliest",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )
    // 存放每条 ConsumerRecord   消费
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](context,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](List("sparkstreamingText"), kafkaParams))
    ds
  }
}
