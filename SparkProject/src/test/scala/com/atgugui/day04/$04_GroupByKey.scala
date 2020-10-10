package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *1,函数签名:  def groupByKey(): RDD[(K, Iterable[V])]
 *            def groupByKey(numPartitions: Int): RDD[(K, Iterable[V])]
 *            def groupByKey(partitioner: Partitioner): RDD[(K, Iterable[V])]
 *2,参数说明:
 *    (1)无参数
 *    (2)分区数
 *    (3)分区器
 *3,函数说明:
 *      将分区的数据直接转换为相同类型的内存的数组进行后续的处理 =>(b,CompactBuffer(3, 1))
 *4,分区器:HashPartitioner
 *
 *
 *5,reduceByKey和GroupByKey的区别
 *    (1)reduceByKey可以对value进行合并
 *       groupByKey只分组不合并
 *    (2)reduceByKey可以在map端进行局部合并
 *       groupByKey在map端没有局部合并
 *
 *
 */
class $04_GroupByKey {

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
   *
   */
  @Test
  def groupByKeyTest03(): Unit ={
    val list = List(("a", 1), ("d", 1), ("a", 1),("b", 3), ("b", 1))
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.groupByKey()
    val rdd3 = rdd.groupByKey(2)
    val rdd4 = rdd.groupByKey(new HashPartitioner(2))
    rdd2.saveAsTextFile("output")
   // rdd3.saveAsTextFile("output2")
    //rdd4.saveAsTextFile("output3")
  }
  @Test
  def text(): Unit ={
    val list = List(
      ("a", 2), ("a", 3), ("b", 4),
      ("b", 2), ("a", 5), ("b", 3)
    )
    val rdd: RDD[(String, Int)] = sc.makeRDD(list, 2)
    val rdd1: RDD[(String, Int)] = rdd.aggregateByKey(0)(
      (x, y) => {
        x.max(y)
      },

      (x, y) => {
        x + y
      }
    )
    println(rdd1.collect().mkString(","))
  }
  /**
   * wordCoount
   */
  @Test
  def groupByKeyWordCount(): Unit ={
    val list = List("a", "b", "a", "b", "c", "d")
    val list1 = list.map(word => (word, 1))
    val rdd = sc.makeRDD(list1)
    val rdd2 = rdd.groupByKey()
    val rdd3 = rdd2.map {
      case (key, iter) => (key, iter.size)
    }
    rdd3.saveAsTextFile("output")

  }

}
