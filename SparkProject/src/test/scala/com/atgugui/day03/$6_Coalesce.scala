package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * def coalesce(numPartitions: Int, shuffle: Boolean = false,
 * partitionCoalescer: Option[PartitionCoalescer] = Option.empty)
 * (implicit ord: Ordering[T] = null): RDD[T]
 *
 * 函数说明:
 * 根据数量缩减分区,用于大数据集过滤后,提高小数据集的执行效率
 * 当spark程序中存在过多的小任务的时候,可以通过coalesce方法,收缩合并分区
 * 减少分区个数,减少任务调度的成本
 */
class $6_Coalesce {

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
   * 缩小分区
   * 没有产生shuffle
   */
  @Test
  def coalesceTest(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 6)
    val rdd2 = rdd.coalesce(2)
    rdd2.saveAsTextFile("output")

  }

  /**
   * Coalesce方法默认情况下无法扩大分区，因为默认不会将数据打乱重新组合。扩大分区是没有意义。
   * 如果想要扩大分区，那么必须使用shuffle，打乱数据，重新组合
   * 此方法Coalesce增大分区不起作用,默认的分区还是上游的分区
   */
  @Test
  def coalesceTest01(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 2)
    val rdd2 = rdd.coalesce(4)
    rdd2.saveAsTextFile("output")
    Thread.sleep(10000)

  }

}
