package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 1,函数签名
 *    def filter(f: T => Boolean): RDD[T]
 * 2,参数:T 任何类型的参数
 * 3,返回值:Boolean类型
 * 4,用途:将分区内的数据根据指定的规则进行筛选过滤,符合规则的数据保留,不符合的数据丢弃
 * 5,对象为:每个分区中的每个数据
 * 6,特点:当数据进行筛选后,分区不变,但分区内的数据可能不均衡,生产环境下,可能出现数据倾斜
 * 比如makeRDD(List(1,1,1,1,1,1,1,2),2) 按分区来过滤的 如果条件为_%2!=0
 * 分区0:(1,1,1,1)
 * 分区1:(1,1,1)
 */
class $3_filter {

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
  def test01(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6),2)
    val rdd2 = rdd.filter(_ % 2 == 0)
    rdd2.foreach(println)
    Thread.sleep(100000)
  }

  /**
   * 出现数据倾斜的情况
   * 原本分区为
   * 分区0:1, 1, 1, 1
   * 分区1:2, 2, 2, 2, 2
   * 过滤后的分区:
   * 分区0:为空
   * 分区1:2, 2, 2, 2, 2
   */
  @Test
  def filterTest01(): Unit ={
    val rdd = sc.makeRDD(List(1, 1, 1, 1, 2, 2, 2, 2, 2),2)
    val rdd2 = rdd.filter(number => number % 2 == 0)
    rdd2.saveAsTextFile("output")
  }

  /**
   * 需求:从服务器日志数据apache.log中获取2015年5月17日的请求路径
   * 按需求过滤数据
   */
    @Test
  def filterTest02(): Unit ={
    // 读取文件
      sc.textFile("input/apache.log")
        .filter(_.split(" ")(3).split(":")(0) == "17/05/2015")
        .map(line=>line.split(" ").last)
        .saveAsTextFile("output")
  }
}
