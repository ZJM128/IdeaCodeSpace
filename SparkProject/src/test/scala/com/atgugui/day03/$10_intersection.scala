package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 	函数签名
 * def intersection(other: RDD[T]): RDD[T]
 * 	函数说明
 * 对源RDD和参数RDD求交集后返回一个新的RDD
 *
 * 求交集:有shuffle,
 * 分区器:结果默认使用HashPartitioner，分上游RDD最大分区数！
 *
 */
class $10_intersection {

  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark"))
  @Before
  def start(): Unit ={
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path,true)
  }
  @After
  def stop(): Unit ={
    sc.stop()
  }

  @Test
  def intersectionTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5),2)
    val rdd1 = sc.makeRDD(List( 2, 3))
    val rdd2 = rdd.intersection(rdd1)
    rdd2.saveAsTextFile("output")
  }

  /**
   * 如果两个RDD类型不一致就报错
   */
  @Test
  def intersectionTest01(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5))
    val rdd1 = sc.makeRDD(List( "2", 3))
    //val rdd2 = rdd.intersection(rdd1)
    //rdd2.foreach(println)
  }

}
