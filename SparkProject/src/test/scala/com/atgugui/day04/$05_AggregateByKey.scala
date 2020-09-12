package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{HashPartitioner, SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *1,函数签名:
 * def aggregateByKey[U: ClassTag](zeroValue: U)(seqOp: (U, V) => U,
 * combOp: (U, U) => U): RDD[(K, U)]
 * 2,函数说明
 *    将数据根据不同的规则进行分区内计算和分区间计算
 * 3,参数
 *    (1)第一个参数列表中的参数表示初始值
 *    (2)第二个参数列表中含有两个参数
 *        2.1 第一个参数表示分区内的计算规则
 *        2.2 第二参数表示分区间的计算规则
 * 4,aggregateByKey:  先按照key进行分组，分组后，将相同key的values进行聚合，聚合后返回一个值！
 * 5,aggregateBykey比reduceByKey更加灵活
 */
class $05_AggregateByKey {

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
  def aggregateByKey(): Unit ={
    val list = List((1, 1), (1, 2), (2, 1), (1,3),(2,2),(2, 2))
    val rdd = sc.makeRDD(list,2)
    val rdd1 = rdd.aggregateByKey(10)(_ + _, _ + _)
    // 等价于
    rdd1.saveAsTextFile("output")
  }

  /**
   * 取出每个分区内相同key的最大值然后分区间相加
   */
    @Test
  def aggregateByKeyTest01(): Unit ={
    val list = List((1, 1), (1, 2), (2, 1), (1,3),(2,2),(2, 2))
    val rdd = sc.makeRDD(list,2)
    val rdd1 = rdd.aggregateByKey(Int.MinValue)((zeroValue, value) => zeroValue.max(value), _ + _)
    rdd1.saveAsTextFile("output")
  }

  /**
   * 分区内同时求最大和最小，分区间合并
   * (1, 1), (1, 2), (2, 1), ==>(1,1) (1,2) (2,1) (2,1)
   * (1,3),(2,2),(2, 2)==>(1,3) (1,3) (2,2) (2,2)
   * ==>(1,(5,4))) (2,(3,3)))
   */
    @Test
  def aggregateByKeyTest02(): Unit ={
    val list = List((1, 1), (1, 2), (2, 1), (1,3),(2,2),(2, 2))
    val rdd = sc.makeRDD(list,2)
    val rdd2 = rdd.aggregateByKey((Int.MinValue, Int.MaxValue))((zeroValue, value) => (zeroValue._1.max(value), zeroValue._2.min(value)),
      (x, y) => (x._1 + y._1, x._2 + y._2))
    rdd2.saveAsTextFile("output'")
  }

  /**
   * 使用模式匹配
   */
    @ Test
  def aggregateByKeyTest03(): Unit = {
    val list = List((1, 1), (1, 2), (2, 1), (1,3),(2,2),(2, 2))
    val rdd = sc.makeRDD(list,2)
    val rdd2 = rdd.aggregateByKey((Int.MinValue, Int.MaxValue))(
      { case ((max, min), value) => (max.max(value), min.min(value)) },
      { case ((max1, min1), (max2, min2)) => (max1 + max2, (min1 + min2)) })
    rdd2.saveAsTextFile("output")
  }

  /**
   * 求每个key对应的平均值
   * 平均值=  sum / count
   */
  @ Test
  def aggregateByKeyTest04(): Unit = {

    val list = List((1, 1), (1, 2), (2, 1), (1,3),(2,2),(2, 2))
    val rdd = sc.makeRDD(list,2)
    val rdd2 = rdd.aggregateByKey((0, 0))(
      { case ((zeroSum, zeroCount), value) => (zeroSum + value, zeroCount + 1) },
      { case ((sumValue, countValue), (sumValue1, countValue1)) => (sumValue + sumValue1, countValue + countValue1) }
    )
    val rdd3 = rdd2.map {
      case (key, (sum, count)) => (key, (sum.toDouble / count))
    }
    rdd3.saveAsTextFile("output")
  }

  @Test
  def wordCount(): Unit ={
    val list = List("a", "b", "b", "b", "e")
    val list1 = list.map((_, 1))
    val rdd = sc.makeRDD(list1)
    // 先对key进行区内分组然后对组内的value值进行操作
    val rdd2 = rdd.aggregateByKey(0)(_ + _, _ + _)
    rdd2.saveAsTextFile("output")
  }
}
