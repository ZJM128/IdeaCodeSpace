package com.atgugui.day02

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

class $06_flatMap {

  private val sparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("my-spark"))

  @Before
  def start():Unit={
    val fileSystem = FileSystem.get(new Configuration())
    val path = new Path("output")
    if(fileSystem.exists(path))
      fileSystem.delete(path,true)
  }

  @After
  def stop():Unit={
    sparkContext.stop()
  }

  @Test
  def flatMap(): Unit ={
    val list = List(List(1, 2, 3), 6, List(8, 9, 10))
    val rdd = sparkContext.makeRDD(list)
    val rdd2 = rdd.flatMap({
      case x: List[int] => x
      case y: Int => List(y)
    })
    rdd2.saveAsTextFile("output")
  }
}
