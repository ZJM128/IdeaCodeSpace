package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**\
 *
 * 	函数签名
 * def union(other: RDD[T]): RDD[T]
 * 	函数说明
 * 对源RDD和参数RDD求并集后返回一个新的RDD
 * =>数据不一致的话会报错
 *
 * union：并集
 * List1(1,2,3,4)
 * List(2,3,4,5)
 * 数学上的并集：  1,2,3,4,5
 * 数据处理中的并集（spark）：  1,2,3,4,2,3,4,5
 * 没有shuffle!  生成的结果的分区数等于上游分区数的总和！
 * 等价于 ++
 *
 *
 *
 */
class $11_Union {
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
   * 没有shuffle
   * 生成的分区数为上游的分区数的总和
   * 数据分区规则,集合的长度/分区数 不能整除,则后面的多一个数据
   */
  @Test
  def testUnion()={
    val rdd = sc.makeRDD(List(1, 2, 3, 4),2)
    val rdd1 = sc.makeRDD(List(4,5,6),2)
    val rdd3 = rdd.union(rdd1)
    rdd3.saveAsTextFile("output")
  }

}
