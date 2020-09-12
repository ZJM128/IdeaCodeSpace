package com.atgugui.day02

import org.apache.spark.{SparkConf, SparkContext}

object Review {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("myspark")
    val sparkContext = new SparkContext(sparkConf)
  /*  val rdd = sparkContext.makeRDD(List(1, 2, 3, 6, 7),2)
    val rdd2 = rdd.mapPartitionsWithIndex((index, list) => {
      List((index, list.max)).iterator
    })
    rdd2.foreach(println)
*/

   // flatMapTest(sparkContext)
    glomTest(sparkContext)
  }
  def flatMapTest(sparkContext:SparkContext): Unit ={
    val list = List(List(1, 2), 3, List(4, 5))
    sparkContext.makeRDD(list)
      .flatMap {
        case list: List[Any] => list
        case x => List(x)
      }
      .foreach(println)
  }

  def glomTest(sparkContext:SparkContext): Unit ={
    val list = List(1, 2, 3, 4, 5, 6)
    val rdd = sparkContext.makeRDD(list)
    rdd.glom().saveAsTextFile("glomOutput")
  }
}
