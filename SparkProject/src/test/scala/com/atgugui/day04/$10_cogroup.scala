package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * cogroup:将RDD1所有的分区中的相同的key的value放在一起
 *        将RDD2所有的分区中的相同的key的value放在一起
 *        然后将两个RDD相同key的value组合在一起,如果
 *        有一个key只有一个RDD有那么没有的那个key的RDD用CompactBuffer()
 *        代替
 *  会产生shuffle
 */
class $10_cogroup {

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
  def testCogroup(): Unit ={
    val list1 = List(("a", 88), ("b", 95),("c",20),("b",93))
    val list2 = List(("a", 2), ("b", 2),("d",30),("a",30))

    val rdd1 = sc.makeRDD(list1)
    val rdd2 = sc.makeRDD(list2)
    rdd1.cogroup(rdd2).saveAsTextFile("output")

  }
}
