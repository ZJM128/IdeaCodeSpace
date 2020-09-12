package com.atgugui.day02

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 和mapPartitions的区别不大,就是多了每个分区的索引
 */
class $05_MapPartitionsWithIndex {

  private val sparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("my-spark"))

  @Before
  def start():Unit={
    val fileSystem = FileSystem.get(new Configuration())
    val path = new Path("output")
    if(fileSystem.exists(path))
      fileSystem.delete(path,true)
  }

  @After
  def stop():Unit={
    sparkContext.stop()
  }

  /**
   * 返回第二个分区的数据
   */
    @Test
  def mapPartitionsWithIndexTest(): Unit ={
    val list = List(1, 2, 3, 4, 5, 6)
    val rdd = sparkContext.makeRDD(list, 2)
    val rdd1 = rdd.mapPartitionsWithIndex((index, iter) => {
      if (index == 1)
        iter
      else {
        // 不符合要求,返回空集合
        Nil.iterator
      }
    })
    rdd1.saveAsTextFile("output")
  }

  @Test
  def mapPartitionsWithIndexMax(): Unit ={
    val rdd = sparkContext.makeRDD(List(1, 2, 3, 4, 5, 6),2)
    val rdd1 = rdd.mapPartitionsWithIndex((index, iter) => {
      List((index, iter.max)).iterator
    })
    rdd1.saveAsTextFile("output")
  }

  @Test
  def test01():Unit={
    val rdd = sparkContext.makeRDD(List(1, 2, 3, 4, 5, 6),2)
    rdd.mapPartitionsWithIndex((index,iter)=>{
      iter.map(data=>(index,data))
    }).saveAsTextFile("output")
  }
}
