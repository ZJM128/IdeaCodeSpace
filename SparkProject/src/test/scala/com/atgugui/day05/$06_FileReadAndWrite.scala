package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 文本的读写操作
 *   (1)读 sc.textFile
 *   (2)写 rdd.saveAsTestFile()
 */
class $06_FileReadAndWrite {

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
    val rdd = sc.textFile("input/1.txt")
    rdd.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .saveAsTextFile("output")
  }

}
