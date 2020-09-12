package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}


/**
 * groupBy(参数)
 * 1,参数:def groupBy[K](f: T => K,p:Partitioner)(implicit kt: ClassTag[K]): RDD[(K, Iterable[T])]
 *  参数1:f:T=>K 是一个函数
 *    函数的形参为:数据集中的一个一个的元素
 *    返回值为:返回要以那个值作为key的值
 *  参数2:
 *    Partitioner,指设定下游的分区的数量,如果不设置,则默认为旧的RDD的分区数量
 * 2,返回值:返回一个元组
 *    元组的第一个元素是分组的key值
 *    元组的第二个元素是 相同的key值形成的可以迭代的集合
 * 3,作用:可以将数据按照某些规则进行分组
 * 4:特点:
 *    (1)分区默认不变
 *    (2)不同分区的数据被重新打乱进入道不同的分区中(有可能产生shuffle)
 *    (3)我们将上游的分区数据打乱重新组合到下游的分区中,这个操作称之为shuffle
 *    (4)极限情况下,所有的数据会被分到一个分区内,所有的key相同的情况下
 *    (5)一个组的数据在一个分区,但是并不是说一个分区只有一个组
 *        如果分组的数量大于分区的数量,那么一个分区里会有多个分组的数据存在
 *        比如makeRDD(List(1,2,2,1,1,3,3),2) ,groupby(x=>x),
 *        3个分组,但只有两个分区,会出现一个分区有多个分组的情况
 * 5,存在问题
 *    groupby之后有可能出现数据倾斜,也就是数据分布不均的情况
 * 6,解决方案:
 *    通过传递参数,改变下游分区的数量
 *
 * 7,补充的知识
 *  只要看到算子,需要传入一个 Partitioner或者numPartitions
 *  此算子一定会产生shuffle,shuffle类型的算子,会创建shuffleRDD
 *
 */

class $2_GroupBy {

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
   * 一个分组有多个分区的数据的情况 分区的个数小于分组的个数
   * 分区0
   * (2,CompactBuffer(2, 2))
   * 分区1
   * (1,CompactBuffer(1, 1, 1))
   * (3,CompactBuffer(3, 3))
   */
  @Test
  def test01()={
    val rdd1 = sc.makeRDD(List(1, 2, 2, 1, 1, 3, 3), 2)
    val rdd2 = rdd1.groupBy(key => key)
    rdd2.saveAsTextFile("output")
  }

  /**
   * 统计奇偶数
   */
  @ Test
  def test02(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7),2)
    // 设定了numberPartitions的数量会产生shuffle
    val rdd2 = rdd.groupBy(x => x % 2,3)
    println(rdd2.partitioner)
    rdd2.saveAsTextFile("output")
    Thread.sleep(100000)
  }

  /**
   * 将List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组
   */
    @ Test
  def groupByFirstWord(): Unit ={
    val rdd = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop"))
    // 字符也是一个集合stringOps
    val rdd2 = rdd.groupBy(word => word(0))
    rdd2.saveAsTextFile("output")
  }

  /**
   * 从服务器日志数据apache.log中获取每个时间段访问量
   */
    @Test
  def groupByTime(): Unit ={
    val rdd = sc.textFile("input/apache.log")
    val rdd2 = rdd.groupBy(line => line.split(" ")(3).split(":")(1))
    val rdd3 = rdd2.map { case (key, iter) => (key, iter.size) }
    rdd3.saveAsTextFile("output")
  }

  /**
   * 统计单词的个数
   */
  @Test
  def wordCount(): Unit ={
    val rdd = sc.textFile("input/apache.log")
    val rdd2 = rdd.flatMap(line => line.split(" "))
      .groupBy(word => word)
      .map { case (key, iter) => (key, iter.size) }
    rdd2.saveAsTextFile("output")
  }
}
