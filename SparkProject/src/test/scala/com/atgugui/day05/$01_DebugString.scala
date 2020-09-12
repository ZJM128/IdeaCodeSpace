package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 *血缘关系:RDD支持粗粒度转换,即在大量记录上执行的单个操作,将创建RDD的一系列Lineage(血统)
 * 记录下来,以便恢复丢失的分区,RDD的Lineage会记录RDD的元数据信息和转换行为,当RDD的部分分区
 * 数据丢失时,他可以根据这些信息来重新运算和恢复丢失的数据分区
 * 血缘关系:记录最后一个RDD的全部依赖关系,类似族谱
 *          作用:当出现部分分区数据丢失的时候可以根据血缘关系进行重新演算对数据进行恢复,保证
 *          了Spark的容错机制
 * 血缘关系的查看:rdd.toDebugString
 */
class $01_DebugString {

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
  def test01():Unit={
    val list = List(0, 2, 2, 4, 4, 6, 7)
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.map(x => (x,1))
    val rdd3 = rdd2.reduceByKey(_ + _)
    rdd3.saveAsTextFile("output")
    // 查看血缘关系
    println(rdd3.toDebugString)
  }
}
