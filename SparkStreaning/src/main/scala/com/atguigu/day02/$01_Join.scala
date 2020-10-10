package com.atguigu.day02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * join:无状态操作
 * (1)使用条件:必须是k-v形式的RDD
 * (2)作用:
 *    a)两个流之间的join需要两个流的批次大小一致,这样才能做到同时触发计算
 *    b)计算过程就是对当前批次的两个流中的各自RDD进行join,与两个RDD的join效果相同
 *
 * rdd1.cogroup(rdd2, new HashPartitioner(2)).flatMapValues(pair =>
 * for (v <- pair._1.iterator; w <- pair._2.iterator) yield (v, w)
 * )
 *
 *   通俗来说:
 *      及时RDD1中的一条记录和RDD2中的所有记录匹配,如果有一条匹配成功 就是一条,如果两条匹配成功
 *      就是两条比如RDD1((a,1)) join RDD2((a,1),(a,1))=>RDD((a,(1,1)),(a,(1,1))))  =>for (v <- pair._1.iterator; w <- pair._2.iterator) yield (v, w)
 */
object $01_Join {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("MySpark")
    val streamingContext = new StreamingContext(conf, Seconds(5))
    val ds = streamingContext.socketTextStream("hadoop103", 22222)
    val ds1 = streamingContext.socketTextStream("hadoop103", 33333)
    val ds2 = ds.transform(rdd => {
      rdd.map(x => (x, 1))
    })
    val ds3 = ds1.map((_, 1))

    ds2.join(ds3).print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
