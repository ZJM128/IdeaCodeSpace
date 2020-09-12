package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 依赖关系:
 *  (1)概念:RDD和RDD的关系
 *  (2)分类:窄依赖,宽依赖
 *        ①窄依赖:父RDD的一个分区,经过转换后,所有的元素都会只放进入子RDD的分区中
 *               子类: rangeDependency
 *                    OneToOneDependency
 *        ②宽依赖:和窄依赖相反,父类的RDD的一份分区经过转换后,里面的元素会被打撒分到
 *        不同的子类RDD中,会产生shuffle,同时需要落盘,shuffle的过程是比较耗时时间的
 *
 *
 *
 */
class $03_Dependencies {

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
  def test01()={
    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list)
    // 查看依赖关系
    // List() rdd是初始化RDD 并没有有其他的RDD转换得来
    // dependencies指的是当前RDD依赖于那些RDD的依赖关系

    println(rdd.dependencies)

    val rdd1 = rdd.map(x => x)
    val rdd2 = rdd1.map(x => x)
    val rdd3 = rdd2.map(x => x)
    val rdd4 = rdd3.map(x => x)
    val rdd5 = rdd4.map(x => x)
    val rdd6 = rdd5.map(x => x)
    val rdd7 = rdd6.map(x => (x,1))
    // 查看依赖关系
    //org.apache.spark.OneToOneDependency@2459319c)
    // 一对一的依赖,一指父RDD的一个分区 经过转换后，所有的元素都会 放入子RDD的一个分区
    //OneToOneDependency是窄依赖的一种表现
    // RangeDependency 另一种窄依赖
    // class OneToOneDependency[T](rdd: RDD[T]) extends NarrowDependency[T](rdd)
    println(rdd7.dependencies)

    val rdd8 = rdd7.reduceByKey(_ + _)
    //List(org.apache.spark.ShuffleDependency@5b5e7036)
    // shuffleDependency是宽依赖
    println(rdd8.dependencies)
  }
}
