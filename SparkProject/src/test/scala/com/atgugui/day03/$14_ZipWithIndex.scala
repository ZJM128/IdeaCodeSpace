package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

class $14_ZipWithIndex {
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
   * zipWithIndex:无参数
   * 作用:当前元素和索引进行zip操作。 索引是在集合中的索引而不是在分区中的索引！
   */
  @ Test
  def zipTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5),2)
    val rdd2 = rdd.zipWithIndex()
    rdd2.saveAsTextFile("output")
  }
}
