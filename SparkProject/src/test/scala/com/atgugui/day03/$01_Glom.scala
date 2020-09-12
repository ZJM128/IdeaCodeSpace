package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

class $01_Glom{
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
   * glom(形参) 算子:
   * 1,作用:将同一个分区内的数据转换成数组
   * 2,参数说明:
   *    ()1),形参为空,无参数
   * 3,返回值:RDD[Array[T]] 返回一个一个数组,数组的数据来自同一个分区
   *
   */
    @Test
  def glomTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 3, 2, 4, 5),2)
      // 把每个分区中的数据组成一个数组,然后返回
      val rdd1 = rdd.glom()
      rdd1.foreach(arr=>println(arr.toBuffer))
  }

  /**
   * 方法1
   * 求每个分区的最大值的和
   */
    @Test
  def glomTest02(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7),2)
    // 把每个分区转换为数组
    val rdd1 = rdd.glom()
    // 把每个分区的最大值求
    val rdd2 = rdd1.map(arr => arr.max)
    // 求和,sum是一个行动算子
    val result = rdd2.sum()
    println(result)
  }

  /**
   * 方法2
   * 求每个分区的最大值的和
   */
    @Test
  def glomTest03(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7), 2)
    // 获取每个分区的最大值
    val rdd2 = rdd.mapPartitions(arr => List(arr.max).iterator)
    val result = rdd2.sum()
    println(result )

  }
}