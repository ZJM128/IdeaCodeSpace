package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * def subtract(other: RDD[T]): RDD[T]
 * 	函数说明
 * 以一个RDD元素为主，去除两个RDD中重复元素，将其他元素保留下来。求差集
 *
 * subtract：差集
 * 使用的是当前RDD的分区器和分区数！
 * 有shuffle
 * 如果两个RDD数据类型不一致怎么办？
 * subtract,union,intersection 要求两个RDD数据类型必须一致！
 */
class $12_Subtract {

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

  /**
   * 求差值:就是过滤掉和对比的RDD一样的元素
   * 结果为:3
   * 有shuffle的产生
   * 分区数:结果的分区数为求差值的那个RDD的分区个数
   * 如果两个RDD的类型不一样 会报错
   */
  @Test
  def subtractTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 4),2)
    val rdd1 = sc.makeRDD(List(1, 2, 4,6,7))
    val rdd2 = rdd.subtract(rdd1)
    println(rdd2.partitioner)
    rdd2.saveAsTextFile("output")
  }

  /**
   * 用的是HashPartitions的分区器
   */
  @Test
  def subtractTest01(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 4,9,5),2)
    val rdd1 = sc.makeRDD(List(1,6,7))
    val rdd2 = rdd.subtract(rdd1)
    println(rdd2.partitioner)
    rdd2.saveAsTextFile("output")
  }
}
