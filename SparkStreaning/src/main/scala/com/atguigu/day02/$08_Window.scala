package com.atguigu.day02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * (1)函数
 *  def window(windowDuration: Duration, slideDuration: Duration): DStream[T]
 * 两个参数:
 *    (1)第一个参数:窗口的大小
 *    (2)第二个参数:窗口滑动的大小
 *  返回值:DS
 *  (2)作用:先定义窗口,在对ds进行操作,更加灵活
 */
object $08_Window {

  // 可以在执行操作的时候,先定义窗口,在定义在窗口的运算逻辑
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(3))
    val ds = streamingContext.socketTextStream("hadoop103", 8888)

    val ds1 = ds.transform(rdd => {
      rdd.flatMap(line => line.split(" ").map(word => (word, 1)));
    })
    // 先定义窗口 之后返回的ds的所有的操作都是使用之前定义的窗口 更加灵活
    val ds2 = ds1.window(Seconds(6), Seconds(3))
      .reduceByKey(_ + _)
    ds2.print(1000)
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
