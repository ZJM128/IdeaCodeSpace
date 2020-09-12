package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

class $14_ZIP {
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
   * spark中的zip和scala中的zip有区别
   * spark中的zip,RDD的要求为
   * (1)分区数要一样
   * (2)分区内的数据个数要相等
   * 分区数:和上游的RDD分区数一样
   * 分区规则:和内存RDD的分区规则一致
   */
  @ Test
  def zipTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5),2)
    val rdd2 = sc.makeRDD(List("a", "b", "c","d","g"),2)
    val rdd3 = rdd.zip(rdd2)
    rdd3.saveAsTextFile("output")
  }
}
