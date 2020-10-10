package com.atguigu.day01

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.internal.Logging

object Test extends  Logging{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("my")
    val sparkContext = new SparkContext(conf)
    val rdd1 = sparkContext.makeRDD(List("t", "t")).map((_, 1))
    val rdd2 = sparkContext.makeRDD(List("t", "t")).map((_, 1))
    val result = rdd1.cogroup(rdd2, new HashPartitioner(2)).flatMapValues(pair =>
      for (v <- pair._1.iterator; w <- pair._2.iterator) yield (v, w)
    )

    rdd1.cogroup(rdd2, new HashPartitioner(2)).foreach(println)
   // result.foreach(println)
  }

}
