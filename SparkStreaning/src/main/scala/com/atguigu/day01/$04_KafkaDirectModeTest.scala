package com.atguigu.day01

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 使用SparkStream当作消费者 消费KafKa的消息
 * 采用消费者了自动提交offset,有可能出现数据丢失的情况,
 * 问:为什么会出现数据丢失
 * 答:因为先提交了offset了,如果在提交完offset后出现异常,
 * 也就是后面的程序还没有来得及处理数据,相对于后面的程序来说
 * 数据是丢失的
 *
 *
 *
 */
object $04_KafkaDirectModeTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("MySpark")
    // 创建streamingContext     指定采集周期为3秒
    val streamingContext = new StreamingContext(conf, Seconds(3))
    // 准备map 其中保存消费者的参数, 参考ConsumerConfig
    val mapKafka = Map[String, String](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
      "enable.auto.commit" -> "true",
      "auto.commit.interval.ms" -> "500",
      "group.id" -> "kafkass",
      "client.id" -> "s1",
      "auto.offset.reset" -> "earliest",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )
    // 存放每条 ConsumerRecord ----消费
    val kafkaDs = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](List("sparkstream"), mapKafka)
    )

    // 取出每条记录的值
    val resultDs = kafkaDs.map(record => {
      if (record.value()=="d"){
        // 模拟异常
       throw new RuntimeException("发生异常")
      }
      record.value()
    })

    resultDs.print(1000)

    // 启动采集器 开始计算逻辑
    streamingContext.start()

    // 阻塞当前线程
    streamingContext.awaitTermination()
  }
}
