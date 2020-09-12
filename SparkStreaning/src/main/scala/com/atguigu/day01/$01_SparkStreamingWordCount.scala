package com.atguigu.day01

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object $01_SparkStreamingWordCount {

  def main(args: Array[String]): Unit = {

    // 核数至少要两个
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStream")

    // 创建采集器 指定3秒为数据的采集周期 到了采集周期就把数据元数据交给Driver处理 再由Driver交个Executor处理
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // 计算逻辑 创建编程模型DStream 收到的是文本数据 以UTF-8编码 以\n分割每行
    val socketDS = ssc.socketTextStream("hadoop103", 44444)

    // ds中的方法称为 高度抽象原语
    val result = socketDS.flatMap(line => line.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)

    // 打印输出  如果没有print方法会报要求失败：未注册任何输出操作，因此无须执行
    result.print(1000)

    // 启动采集器 开始计算
    ssc.start()

    // 等待采集器的结束 会阻塞当前main线程,一直到应用停止
    ssc.awaitTermination()
  }

}
