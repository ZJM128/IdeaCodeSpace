package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkContext.jarOfObject
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *1,函数签名:  def reduceByKey(func: (V, V) => V): RDD[(K, V)]
 *            def reduceByKey(func: (V, V) => V): RDD[(K, V)]
 *            def reduceByKey(func: (V, V) => V, numPartitions: Int): RDD[(K, V)]
 *2,参数说明:函数=>传入(v,v)两个v类型的返回v类型的
 *3,返回值:k-v
 *4,分区器:HashPartitioner
 *5,作用的对象:
 *        对分区内相同key的value进行逻辑处理(传入的函数的逻辑)
 *6,mapSideCombine: Boolean = true =>可以在map端进行对数据的合并
 *
 */
class $03_ReduceByKey {

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

  @Test
  def reduceByKeyTest(): Unit ={
    val list = List((2, 1), (2, 1), (3, 1),(3, 3), (4, 1))
    val rdd = sc.makeRDD(list,2)
    val rdd1 = rdd.reduceByKey((v1, v2) => v1 + v2)
    rdd1.saveAsTextFile("output")
  }

  @Test
  def reduceByKeyTest03(): Unit ={
    val list = List(("a", 1), ("a", 1), (3, 1),(3, 3), ("b", 1))
    val rdd = sc.makeRDD(list,2)
    val rdd2 = rdd.reduceByKey(_ + _)
    rdd2.saveAsTextFile("output")
  }

  @Test
  def reduceByKeyWordCount(): Unit ={
    val list = List("a", "b", "a", "b", "c", "d")
    val list1 = list.map(word => (word, 1))
    val rdd = sc.makeRDD(list1)
    val rdd2 = rdd.reduceByKey(_ + _)
    rdd2.saveAsTextFile("output1")

  }
}
