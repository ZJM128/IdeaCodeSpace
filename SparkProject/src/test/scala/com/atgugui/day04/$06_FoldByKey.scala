package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *1,函数签名:def foldByKey(zeroValue: V)(func: (V, V) => V): RDD[(K, V)]
 *2,函数说明:当分区内计算规则和分区计算规则相同的时候 aggregateByKey就可以简化
 * 为flodByKey
 *
 */
class $06_FoldByKey {

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
   * 分区内和分区间的规则一样的时候foldByKey比较简化
   * (1, 1), (1, 2), (2, 1)===> (1,13) (2,11)
   * (1,3),(2,2),(2, 2)==>(1,13),(2,14)
   * ==>(2,25) (1,26)
   *
   */
  @Test
  def foldByKeyTest01(): Unit ={
    val list = List((1, 1), (1, 2), (2, 1), (1,3),(2,2),(2, 2))
    val rdd = sc.makeRDD(list,2)
    val rdd2 = rdd.foldByKey(0)(_ + _)
    // 等价于
    //                 (零值)           (分区内逻辑,分区间逻辑)
    val rdd3 = rdd.aggregateByKey(0)(_ + _, _ + _)
    // 等价于
    val rdd4 = rdd.reduceByKey(_ + _)
    rdd2.saveAsTextFile("output")
    rdd3.saveAsTextFile("output1")
    rdd4.saveAsTextFile("output2'")

    }

}
