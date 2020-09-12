package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 练习
 */
class $11_Test {

  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark"))

  @Before
  def start(): Unit = {
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path, true)
  }

  @After
  def stop(): Unit = {
    sc.stop()
  }

  /**           1516609143867 6    7     64    16
   * agent.log：时间戳，     省份，城市，用户，广告，中间字段使用空格分隔
   *
   * 统计出每一个省份 广告被点击数量Top3  广告
   * 结果 <= 省份数量 * 3
   *
  数据的粒度：  数据集中一条数据代表的意义
                          某个用户在某一时刻，点击广告的行为记录  (时间戳，省份，城市，用户，广告)
   */
    @Test
  def test01():Unit={
      // 读取数据
    val rdd = sc.textFile("input/agent.log")
    // 列裁剪
    val rdd2 = rdd.map(line => line.split(" ")).map(arr =>( (arr(1), arr.last),1))
    // 统计
    val rdd3 = rdd2.groupByKey()
    val rdd4 = rdd3.map { case ((provice, agv), iter) => (provice, (agv, iter.size)) }
    //排序
    val rdd5 = rdd4.groupByKey()
    val rdd6 = rdd5.mapValues(iter => iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3))
    rdd6.saveAsTextFile("output")
  }

  @Test
  def test02():Unit= {
    // 读取数据
    val rdd = sc.textFile("input/agent.log")
    val rdd2 = rdd.map(line => {
      val word = line.split(" ")
      ((word(1), word.last),1)
    })
    // 统计
    val rdd3 = rdd2.reduceByKey(_ + _)
    // 列裁剪
    val rdd4 = rdd3.map { case ((provice, agv), total) => (provice, (agv, total)) }
    // 分组
    val rdd5 = rdd4.groupByKey()
    // 排序 取top3
    val rdd6 = rdd5.mapValues(iter => iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3))
    // 输出
    rdd6.saveAsTextFile("output01")
    rdd.collect()

  }

}
