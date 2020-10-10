package com.atguigu.day02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 无状态的reduceByKeyAndWindow
 * 1,和时间相关的概念
 *      采集周期：  数据采集的时间周期
 *      Job提交的间隔（滑动的间隔）：  Job每次提交运行的间隔时间
 *      数据的时间范围（窗口的间隔）：   Job计算的数据的时间范围
 *      只要看到   xxxxxAndWindow说明这个算子必须在指定的窗口中运行！
 *  2,WordCount就是:采集周期=job提交的间隔(移动的步长)=数据的时间范围(窗口的大小)
 *
 *  3,如果采集周期,job的提交时间,数据的时间范围不一致是就需要窗口操作
 *  4,注意:滑动步长和窗口大小都必须是采集周期的整数倍
 *  默认job的提交时间和采集周期是一样的
 *
 * 5,只要看到   xxxxxAndWindow说明这个算子必须在指定的窗口中运行！
 * 窗口：  定义了采集周期,job提交时间,数据的时间范围 三个参数的一个对象
 *
 * 6,k-v的ds才能使用该方法 主要对value进行操作
 *
 * 7 函数
def reduceByKeyAndWindow(
      reduceFunc: (V, V) => V,
      windowDuration: Duration,
      slideDuration: Duration
    )
 * 参数解析:
 *    第一个参数:对两个value值进行的操作
 *    第二个参数:数据时间范围(窗口函数)
 *    第三个参数:job提交的时间(滑动窗口的大小)
 *
 *  8,如果采集周期和提交Job的间隔一致，可以省略slideDuration，默认就是采集周期  上述代码等价下面的代码
 */
object $06_ReduceByKeyAndWindow {

  /**
   * 需求：  在采集周期为5s的环境中，每间隔5s，统计过去10s 采集的单词的个数
   *      采集周期：  5s
   *      Job提交的间隔（滑动步长）：  5s
   *      数据的时间范围（窗口）：   10s
   *
   */
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(2))

    val ds = streamingContext.socketTextStream("hadoop103", 8888)

    val ds1 = ds.flatMap(line => line.split(" ").map(word => (word, 1)))

    val ds2 = ds1.reduceByKeyAndWindow(_+_,windowDuration=Seconds(4),slideDuration=Seconds(2))

    ds2.print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()
  }

  def test(): Unit ={
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(2))

    val ds = streamingContext.socketTextStream("hadoop103", 8888)

    // 使用 transform可以直接对ds中每个采集周期的rdd进行处理
    val ds1 = ds.transform(rdd=>{
      rdd.flatMap(line=>line.split(" ").map(word=>(word,1)))
    })

    // 核心还是reduceByKey
    val ds2 = ds1.reduceByKeyAndWindow(_+_,windowDuration=Seconds(4),slideDuration=Seconds(2))
    // 如果采集周期和提交Job的间隔一致，可以省略slideDuration，默认就是采集周期  上述代码等价下面的代码
    //val ds2: DStream[(String, Int)] = ds1.reduceByKeyAndWindow(_ + _, windowDuration=Seconds(10))
    ds2.print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
