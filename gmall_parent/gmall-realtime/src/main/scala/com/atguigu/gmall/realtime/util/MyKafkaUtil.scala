package com.atguigu.gmall.realtime.util
import java.lang

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent

import scala.collection.mutable

object MyKafkaUtil {
  private val kafkaParams: mutable.Map[String, Object] = mutable.Map[String, Object](
    "bootstrap.servers" -> ConfigUtil.getProperty("kafka.servers"),
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> ConfigUtil.getProperty("kafka.group"),
    "auto.offset.reset" -> "latest", // 如果有保存 offset, 则从保存位置开始消费, 没有则从latest开始消费
    "enable.auto.commit" -> (true: lang.Boolean)
  )
  def getKafkaStream(ssc:StreamingContext,topic:String)={
    KafkaUtils.createDirectStream[String,String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](Set(topic), kafkaParams)
    ).map(_.value)

  }

}
