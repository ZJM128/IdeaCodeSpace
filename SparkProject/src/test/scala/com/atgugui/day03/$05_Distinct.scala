package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * def distinct()(implicit ord: Ordering[T] = null): RDD[T]
 * def distinct(numPartitions: Int)(implicit ord: Ordering[T] = null): RDD[T]
 *
 * 1,参数说明:将数据集中重复的数据去重
 * 2,过程:因为需要把数据集合在一个分区内,然后去重,所以会产生shuffle
 * 3.distinct实现的原理
    case _ => map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
        (1,2,2) =>map => (1,null),(2,null),(2,null)
        => reduceByKey => (1,null) =>  (1,null)  (2,null),(2,null)     (2,null)
        => map(_._1) => (1,2)
        distinct去重的原理就是对key进行分组，分组后去重！
        select distinct(xxx)
        等价于
        select xxx
        from xx
        group by xxx
 *
 *
 *
 *
 *
 */
class $05_Distinct {

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
   *  采用的是HashPartitioner
   *  (key.hash)%分区数
   *  产生shuffle
   *  注:如果当前RDD有分区器，且分区的数量和 distinct(numPartions)相同，就不会产生shuffle!
   */
  @Test
  def distinctTest01(): Unit ={
    val rdd = sc.makeRDD(List(8, 2, 10, 4, 5, 6, 2, 2, 2, 4),2)
    val rdd2 = rdd.distinct()
    val rdd3 = rdd.distinct(2)
    rdd2.saveAsTextFile("output")
    rdd3.saveAsTextFile("output2")
    Thread.sleep(100003)
  }

  /**
   * 使用groupby实现distinct功能
   * 参数numPartitions 会产生shuffle
   */
  @Test
  def groupByToDistinct(): Unit ={
    val rdd = sc.makeRDD(List(8, 2, 10, 4, 5, 6, 2, 2, 2, 4))
    val rdd2 = rdd.groupBy(number => number, 2)
    val rdd3 = rdd2.keys
    rdd3.saveAsTextFile("output")
  }
}
