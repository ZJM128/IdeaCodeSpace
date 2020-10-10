package com.atguigu.day01

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object $02_StreamingQueueDstream {
  /**
   * 测试代码逻辑:可以提供一个队列 将RDD不断放入队列中
   *  从队列中取得RDD 进行处理
   *
   */
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("my spark")
    // 创建应用的环境 指定3秒为数据的采集周期
    val streamingContext = new StreamingContext(conf, Seconds(3))

    // 构建队列
    val queue = mutable.Queue[RDD[String]]()

    // 创建queueString对象 oneAtATime 设置每次消费队列中多少个元素
    val ds = streamingContext.queueStream(queue, oneAtATime = false)

    //打印输出
    ds.print(1000)
    // 启动应用,开始计算
    streamingContext.start()


    // 模拟向队列中放入RDD 此时receiver一直在从queue中拉取数据 相当于监听着队列
    val rdd = streamingContext.sparkContext.makeRDD(List("good kafka"))
    for (i<-1 to 100){
      queue.enqueue(rdd)
      Thread.sleep(1000)
    }

    // 会阻塞当前的main线程,一直到应用停止 此处类似 一直在监听着queue是否有元素
    streamingContext.awaitTermination()

  }
}
