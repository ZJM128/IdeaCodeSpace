package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *1,函数签名:def mapValues[U](f: V => U): RDD[(K, U)]
 *2,参数:函数 =>函数传入value类型可以输出另一种类型
 *3,返回值:是一个元组,value和参数的函数的返回值类型一致
 */
class $02_MapValues {

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
   * 将k-v rdd中的v经过函数运算为 U，之后返回K-U
   */
  @Test
  def mapValuesTest(): Unit ={
    val list = List((1, 1), (2, 1), (3, 1), (4, 1))
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.mapValues(_ + "33")
    rdd2.saveAsTextFile("output")
  }
}
