package com.atguigu.day01

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 使用SparkStream当作消费者 消费KafKa的消息
    基于自动提交offset会出现数据的丢失,所以改为手动提交offset,结合CheckPoint技术
    可以在发生异常的时候通过CheckPoint保留的数据恢复发生异常的前的数据(offset)
 *弊端:每个周期采集的数据都会生成一个CheckPoint文件,也就是会产生大量的小文件,因为CheckPoint产生的文件会存在HDFS中
 * 而HDFS对小文件也不好处理,从而也就降低了性能
 *
 */
object $05_KafkaDirectModeCKTest {

  def main(args: Array[String]): Unit = {

    // 函数
    var getMySC=()=>{
      val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("MySpark")
      // 创建streamingContext     指定采集周期为3秒
      val streamingContext = new StreamingContext(conf, Seconds(3))

      //设置DS 周期型存储的目录 每一个周期采集的数据都会生成一个checkPoint文件
      streamingContext.checkpoint("kafka")

      // 准备map 其中保存消费者的参数, 参考ConsumerConfig
      val mapKafka: Map[String, String] = Map[String, String](
        "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
        "enable.auto.commit" -> "false",
        "auto.commit.interval.ms" -> "500",
        "group.id" -> "kafkack",
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
          //throw new UnknownError("发生异常")
        }
        record.value()
      })
      resultDs.print(1000)
      streamingContext
    }
    // 出现异常后可以从之前保留的状态重新建立接收器 从ck目录根据之前的状态重建 应用
    //从本地的ck目录中的去之前保存的状态信息,根据状态信息通过指定的函数重建streamingContext和之前的状态信息
    val streamingContext: StreamingContext = StreamingContext.getActiveOrCreate("kafka", getMySC)
    // 启动采集器 开始计算逻辑
    streamingContext.start()
    // 阻塞当前线程
    streamingContext.awaitTermination()
  }
}
