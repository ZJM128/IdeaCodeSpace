package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 1,函数签名
 * def sample(
 *  withReplacement: Boolean,
 *  fraction: Double,
 *  seed: Long = Utils.random.nextLong): RDD[T]
 *  2,参数解释:
 *  情况1:
 * 抽取数据不放回（伯努利算法）
 *    伯努利算法：又叫0、1分布。例如扔硬币，要么正面，要么反面。
 *    具体实现：根据种子和随机算法算出一个数和第二个参数设置几率比较，小于第二个参数要，大于不要
 *    第一个参数:抽取的数据是否放回,false表示不返回
 *    第二个参数:抽取的几率,范围在[0-1]之前,0:全不取,1:全取,代表每个元素抽取的概率
 *    第三个参数:随机种子,=>"如果设置了种子"那么程序运行的结果都一样,种什么得到什么
 *  情况2:
 * 抽取数据放回（泊松算法）
 *  第一个参数:抽取的数据是否放回,true:放回,false:不放回
 *  第二个参数:重复数据的几率,方范围大于等于0,表示每一个元素被期望抽取到的次数
 *  第三个参数:随机数种子
 *  3,用途:在实际的开发中,往往会出现数据倾斜的情况,那么可以从数据倾斜的分区中抽取数据,查看数据的规则
 *  分析后,可以进行改善处理,让数据更加均匀
 *
 */
class $04_Sample {
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
  * 不放回=>伯努利算法
  * sample 针对的对象是:每个分区的数据,不会产生shuffle
  * fraction的范围[0-1]
  */
 @Test
 def testSample(): Unit ={
  val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6),2)
  val rdd2 = rdd.sample(withReplacement = false, 0.4)
  rdd2.saveAsTextFile("output")

 }

 /**
  * 放回=>泊松算法
  * sample 针对的对象是:每个分区的数据,不会产生shuffle
  * fraction范围:大于0
  */
 @Test
 def testSample01(): Unit ={
  val rdd = sc.makeRDD(List(1, 2, 2, 3, 4, 5))
  val rdd2 = rdd.sample(withReplacement = true, 2)
  rdd2.saveAsTextFile("output")
 }

 /**
  * 2,3,5
  * 1,2,2,4,5
  * 2,2,3,3,5
  * 2,3,4
  * 每次运行的结果都是一样的
  * 种子一致,结果一样
  */
 @Test
 def testSample02(): Unit ={
  val rdd = sc.makeRDD(List(1, 2, 2, 3, 4, 5),2)
  val rdd1 = rdd.sample(withReplacement = false, 0.4, 4)
  val rdd2 = rdd.sample(withReplacement = false, 0.5, 4)
  val rdd3 = rdd.sample(withReplacement = true, 0.5, 4)
  val rdd4 = rdd.sample(withReplacement = true, 0.4, 4)
  println(rdd1.collect().mkString(","))
  println(rdd2.collect().mkString(","))
  println(rdd3.collect().mkString(","))
  println(rdd4.collect().mkString(","))
 }


}
