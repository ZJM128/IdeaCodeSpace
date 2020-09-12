package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

class $14_ZipPartitions {
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
   * 由于spark的zip方法使用条件比较需要分区数和分区内的元素的个数要一致
   * zipPartitions可以解决分区间元素不一致的问题
   * 在柯里化的第二个参数中传入((iter1,iter2)=>iter1.zipAll(iter2,7,"g"))
   * 7:代表 如果rdd2的元素个数多余rdd1的元素个数 则默认使用7和rdd2剩余的元素进行匹配
   * "g"代表 如果rdd1的元素个数多于rdd2的元素的个数,则默认使用"g"和rdd1的剩余的元素进行匹配
   *
   * 要求:rdd1和rdd2的分区数要一样,不然会报错
   * Can't zip RDDs with unequal numbers of partitions
   */
  @ Test
  def zipTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5),2)
    val rdd2 = sc.makeRDD(List("a", "b"),2)
    val rdd3 = rdd.zipPartitions(rdd2)((iter1,iter2)=>iter1.zipAll(iter2,7,"g"))
    rdd3.saveAsTextFile("output")
  }
}
