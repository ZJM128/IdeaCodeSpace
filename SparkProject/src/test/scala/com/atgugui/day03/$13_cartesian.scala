package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * cartesian:笛卡尔积
 * 没有shuffle
 */

class $13_cartesian {

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
   * 主RDD中的每个分区和辅的RDD的每个分区拼接 形成一个一个元组
   * 分区个数:每个RDD的分区个数相乘
   *
   */
  @Test
  def cartesianTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6),2)
    val rdd1 = sc.makeRDD(List( 5, 6),2)
    val rdd3 = rdd.cartesian(rdd1)
    rdd3.saveAsTextFile("output")
    Thread.sleep(1000000)
  }

}
