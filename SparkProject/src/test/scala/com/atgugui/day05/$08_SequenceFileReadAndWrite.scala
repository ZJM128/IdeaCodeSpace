package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *    读写序列化文件：
 *    写：   RDD.saveAsSequenceFile()
 *    读：   sparkContext.sequenceFile
 *
 *    必须是键值对rdd才能使用序列化
 */
class $08_SequenceFileReadAndWrite {

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
  def test01(): Unit ={
    val list = List(1, 2, 3, 4, 5, 6)
    val rdd = sc.makeRDD(list, 2)
    val rdd2 = rdd.map((_, 1))
    // 以序列化的格式写出文件
    rdd2.saveAsSequenceFile("output")

    // 读序列化文件
    val rdd3 = sc.sequenceFile[Int, Int]("output")
    rdd3.foreach(println)
  }

}
