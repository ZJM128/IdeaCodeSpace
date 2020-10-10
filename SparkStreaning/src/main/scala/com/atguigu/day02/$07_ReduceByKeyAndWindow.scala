package com.atguigu.day02

import org.apache.spark.{SPARK_BRANCH, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 1,有状态的reduceByKeyAndWindow
 * 需求：  在采集周期为5s的环境中，每间隔5s，统计过去24h 采集的单词的个数   采集周期：  5s
 *         Job提交的间隔（滑动步长）：  5s
 *         数据的时间范围（窗口）：   24h
 *
 *
 *  2,作用:由于窗口的范围比较大逐个把采集周期的数据进行处理的话,会比较消耗性能
 *  因而可以采用有状态的reduceByKeyAndWindow 对处理过的数据进行缓存
 *  然后利用缓存的数据减去不符合窗口的数据 加上符合窗口的数据
 *
 * 3,函数
 * def reduceByKeyAndWindow(
 *      reduceFunc: (V, V) => V,
 *      invReduceFunc: (V, V) => V,
 *      windowDuration: Duration,
 *      slideDuration: Duration = self.slideDuration,
 *      numPartitions: Int = ssc.sc.defaultParallelism,
 *      filterFunc: ((K, V)) => Boolean = null
 * ):
 * 参数说明:
 *    (1)第一个参数:有两个参数的函数 返回值和函数的参数一致 该函数的功能是对相同key的缓存历史数据和value进行操作
 *    (2)第二个参数:有两个参数的函数 返回值和函数的参数一致 该函数的功能是对相同key的缓存历史数据和不符合窗口的value进行操作
 *    (3)第三个参数:窗口的大小
 *    (4)第四个参数:窗口滑动的大小
 *    (5)第五个参数:分区的大小
 *    (6)第六个参数:对窗口的数据进行过滤操作
 */

object $07_ReduceByKeyAndWindow {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    // 设置缓存数据
    streamingContext.checkpoint("ReduceByKeyAndWindow")
    val ds = streamingContext.socketTextStream("hadoop103", 8888)

    // 使用 transform可以直接对ds中每个采集周期的rdd进行处理
    val ds1 = ds.transform(rdd=>{
      rdd.flatMap(line=>line.split(" ").map(word=>(word,1)))
    })

    // 利用缓存数据进行计算
    val ds2 = ds1.reduceByKeyAndWindow(_+_, _-_,
      Seconds(6),
      Seconds(3),
     filterFunc = _._2>0)

    ds2.print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
