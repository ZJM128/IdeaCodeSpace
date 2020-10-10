package com.atguigu.day02

/**
 *  (1)DS 离散化数据流 本质是将每个采集周期收到的数据封装为1个RDD 交给job处理,底层还是RDD
 *  如果希望调用RDD的方法对DS进行处理 则可以使用foreachRDD
 *  (2)时刻记得spark的是分Driver分配,executor执行的,
 *  foreachRDD可以对每个rdd进行处理,也可以调用RDD原生的API 比如foreachPartition 对每个
 *  分区中的数据进行批量处理
 *
 */

import org.apache.spark.{SPARK_BRANCH, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object $03_ForeachRDD {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    // 计算逻辑 创建编程模型DSteam 收到的是文本数据,y以UTF-8编码 以\n分割每行
    val ds = streamingContext.socketTextStream("hadoop103", 8888)
    // DS 离散化数据流 本质是将每个采集周期收到的数据封装为1个RDD 交给job处理,底层还是RDD
    // 如果希望调用RDD的方法对DS进行处理 则可以使用foreachRDD
   // 在driver端执行
    ds.foreachRDD(rdd => {
      println(rdd.flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_ + _)
        .collect()
        .mkString(","))
    })

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
