package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 读写对象文件：
 * 写：   RDD.saveAsObjectFile()
 * 读：   sparkContext.objectFile
 */
class $07_ObjectFileReadAndWrite {

  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark"))

  @Before
  def start(): Unit = {
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path, true)
  }

  @After
  def stop(): Unit = {
    sc.stop()
  }

  @Test
  def test01()={
    val list = List(1, 2, 3, 4, 5)
    val rdd = sc.makeRDD(list, 2)
    // 写出
    rdd.saveAsObjectFile("output")

    // 读入
    val rdd2 = sc.objectFile("output")
    rdd2.foreach(println)
  }

}
