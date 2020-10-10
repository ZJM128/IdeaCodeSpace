package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * checkpoint
 *   (1)会删除当前rdd的血缘关系,不会对容错机制产生影响,因为checkpoint的数据是存储在文件系统(HDFS)中的
 *   (2)checkPoint会在rdd的第一个行动算子执行时执行
 *   (3)设置ck目录,如果是集群模式,必须是HDFS上的路径
 *   (4)使用cache和checkPoint相结合,避免checkPoint的job重复计算
 */
class $04_CheckPoint {

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

  /**
   * 每次提交checkPoint都需要重新计算一次RDD
   * 主要是为了保证数据的准确性
   */
  @Test
  def test01():Unit={
    val list = List(1, 2, 3, 4)
    val rdd: RDD[Int] = sc.makeRDD(list, 2)
    val rdd3: RDD[Int] = rdd.map(x => x).map(x => x).map(x => x).map(x => x).map(x => x).map(x => x).map(x => x)
    val rdd2 = rdd3.map(x => {
      println("map")
      x
    })
    println("之前的血缘关系")
    println(rdd2.toDebugString)
    // 设置checkPoint的存放位置
    sc.setCheckpointDir("checkPoint")

    // 使用checkPoint
    // 和cache的不同 类似行动算子,真正运行也是需要第一个行动算子触发,checkPoint才能起作用
    rdd2.checkpoint()

    println("之后的血缘关系")
    sc.setCheckpointDir("checkPoint")
    println("&"*30)
   // 发现当前rdd2需要checkPoint 会在saveAsTextFile后将rdd2的数据写入到文件系统中
    rdd2.saveAsTextFile("output")
    println("%"*20)
    // 从ck目录中取rdd2
    rdd2.foreach(print)
  }

  /**
   * 官方建议:使用cache和checkPoint相结合 可以避免checkPoint的重复计算
   */
  @Test
  def test02():Unit={
    val list = List(1, 2, 3, 4)
    val rdd: RDD[Int] = sc.makeRDD(list, 2)
    val rdd3: RDD[Int] = rdd.map(x => x).map(x => x).map(x => x).map(x => x).map(x => x).map(x => x).map(x => x)
    val rdd2 = rdd3.map(x => {
      println("map")
      x
    })
    println("之前的血缘关系")
    println(rdd2.toDebugString)
    // 设置checkPoint的存放位置
    sc.setCheckpointDir("checkPoint")

    // 使用checkPoint
    // 和cache的不同 类似行动算子,真正运行也是需要第一个行动算子触发,checkPoint才能起作用
    rdd2.checkpoint()

    // 将RDD2先缓存到内存中,避免checkPoint 提交job后重写计算RDD
    rdd2.cache()

    println("之后的血缘关系")
    sc.setCheckpointDir("checkPoint")
    println("&"*30)
    // 从ck目录中取rdd2
    rdd2.saveAsTextFile("output")
    println("%"*20)
    // 第二次取rdd2的数据的时候rdd2的状态已经存在了文件系统中了,可以直接取,不需要重新演算整个血缘关系
    rdd2.foreach(print)
  }


  @Test
  def testWordCount(): Unit ={
    val rdd = sc.makeRDD(List("hello good", "hello good"))

    val rdd1 = rdd.flatMap(line => line.split(" ").map(word => (word, 1)))
      .reduceByKey(_ + _,2)
    rdd1.foreach(println)
   // println(rdd1.toDebugString)
   // println(rdd1.partitions.length)

  }
}
