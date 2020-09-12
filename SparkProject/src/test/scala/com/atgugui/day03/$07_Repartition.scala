package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * def repartition(numPartitions: Int)(implicit ord: Ordering[T] = null): RDD[T]
 *
 * 1,源码:
 * def repartition(numPartitions: Int)(implicit ord: Ordering[T] = null): RDD[T] = withScope {
 *  coalesce(numPartitions, shuffle = true)
 * }
 * 2,函数说明
 * 该操作内部其实执行的是coalesce操作，参数shuffle的默认值为true。无论是将分区数多的RDD转换为分区数少的RDD，
 * 还是将分区数少的RDD转换为分区数多的RDD，repartition操作都可以完成，因为无论如何都会经shuffle过程
 *  3,coalesce和repartition的区别
 *  repartition底层就是coalesce,只不过repartition肯定使用了shuffle的操作,让数据更加均衡一些
 *  可以有效防止数据倾斜的问题
 *  如果缩减分区使用coalesce 增加分区使用repartition
 *
 */
class $07_Repartition {

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
   * 会走shuffle
   * 使用的是HashPartitioner分区器
   */
  @Test
  def repartitionTest(): Unit ={
    val list = List(1, 2, 3, 4, 5, 6, 7)
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.repartition(2)
    rdd2.saveAsTextFile("output")
  }

  /**
   * 缩减和增加都可以
   */
  @ Test
  def repartitionTest01(): Unit ={
    val list = List(1, 2, 3, 4, 5, 6, 7)
    val rdd = sc.makeRDD(list,4)
    val rdd2 = rdd.repartition(2)
    rdd2.saveAsTextFile("output")
  }

}
