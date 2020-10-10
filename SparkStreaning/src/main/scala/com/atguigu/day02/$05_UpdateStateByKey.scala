package com.atguigu.day02

import org.apache.spark.{Partitioner, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * --1. updateStateByKey(形参)
 * --2. 泛型：[S: ClassTag]：指定缓冲区中数据的泛型和操作返回值的v返回值类型
 * --3. 形参：是一个函数，
 *      函数有两个形参
 *      形参1：seq：Seq[V]：相同key的value的集合
 *      形参2：buffer：Option[S]：缓冲区，相同key的缓冲区数据，有可能为空
 *      函数的返回值：Option[S]=>some(值)
 * --4. 操作的返回值：DStream[(K, S)]：
 *        是一个新的DStream，其内部的RDD序列是由每个时间区间对应的(键，状态)对组成的
 *
 * --5. 作用：
 *    计算的最后结果需要使用到前面的采集周期的计算结果，如计算wordcount
 *
 * --6. 说明：
 *     a、用于键值对形式的DStream
 *     b、使用updateStateByKey需要对检查点目录进行配置，会使用检查点来保存状态
 * -- 7 实现原理
 *      对key进行分组,把相同key的value值进行汇总,取出CheckPoint中的缓存的相同的历史数据
 *      再进行汇总,把得到的结果再缓存到CheckPoint中
 */
object $05_UpdateStateByKey {

  /**
   *      进行 累计或汇总的统计
   *      程序从运行起 统计所用的单词的总个数
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    // 因为使用到到了缓存 所以需要使用CheckPoint技术  每次启动都是新建了一个StreamingContext
    streamingContext.checkpoint("UpdateStateByKey")

    val ds = streamingContext.socketTextStream("hadoop103", 8888)

    val ds2 = ds.flatMap(line => line.split(" ").map(word => (word, 1)))
      //                                                    相同key的values集合    缓冲区中相同key的状态(历史数据)
    val ds3 = ds2.updateStateByKey((values: Seq[Int], sum: Option[Int]) => Some(values.sum + sum.getOrElse(0)))
    ds3.print(1000)
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
