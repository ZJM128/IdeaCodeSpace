package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * partitionBy:用什么分区器进行分区
 * 1,函数签名:
 *def partitionBy(partitioner: Partitioner): RDD[(K, V)]
 * 2,参数说明:
 *     partitioner 分区器
 * 3,返回值:RDD[(K,V)]=>k-v键值对类型的
 * 4,操作的对象:分区内每个元素的key值
 * 5,结果:key按分区器的规则进行分区,过程会产生shuffle
 * 6,自定义分区器:
 *     (1)继承Partitioner类
 *     (2)重写numPartitions属性和getPartition方法
 *     (3)rdd.PartitionBy(自定分区器)
 *7,分区器的分类
 *     (1)HashPartitioner
 *     (2)RangePartitioner
 *     (3)自定义分区器
 */
case class Person(var name:String,var age:Int)

/**
 * 自定义分区器
 * @param num
 */
class MyPartitioner(num:Int)extends Partitioner{

  override def numPartitions: Int = num
  override def getPartition(key: Any): Int = {
      if (!key.isInstanceOf[Person]){
        0
      }else{
        println("aa")
        val person = key.asInstanceOf[Person]
        person.age.hashCode()%numPartitions
      }
  }
}
class $01_PartitionBy {

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
  def partitionByTest(): Unit ={
    val rdd: RDD[(Int, String)] =
      sc.makeRDD(Array((1,"aaa"),(2,"bbb"),(3,"ccc")),3)

    val rdd2: RDD[(Int, String)] = rdd.partitionBy(new HashPartitioner(2))

    rdd2.saveAsTextFile("output")

  }

  /**
   * 传入的分区器为HashPartitioner
   * 按每个元素的hash除以分区数 得到该分到哪个区中
   * 面向的对象:每个元素的Key值
   */
  @Test
  def partitionByTest02(): Unit ={
    val list = List(1, 2, 3, 4, 5, 6)
    val list1 = list.map(x => (x, "a"))
    val rdd = sc.makeRDD(list1)
    val rdd2 = rdd.partitionBy(new HashPartitioner(2))
    rdd2.saveAsTextFile("output")
  }

  @Test
  def PartitionByTest03(): Unit ={
    val list = List(1, 3, 2, 4, 6, 7, 8)
    val rdd = sc.makeRDD(list)
    val rdd1 = rdd.map(x => (x, "a"))
    val rdd2 = rdd1.partitionBy(new HashPartitioner(2))
    rdd2.saveAsTextFile("output")
    Thread.sleep(1000000)
  }

  /**
   * 引用自定义分区器
   */
  @Test
  def partitionByTestByCreataPartition(): Unit ={
    val list = List(Person("lisi", 22), Person("wangwu", 25), Person("zhaoliu", 35))
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.map((_, 1))
    // 引入自定义分区器
    val rdd3 = rdd2.partitionBy(new MyPartitioner(2))
    rdd3.saveAsTextFile("output")
  }

  /**
   * 上游的分区器和下游的分区器一样,分区的结果还是和上游的分区结果一样
   *
   */
  @Test
  def repeatPartition(): Unit ={
    val list = List(Person("lisi", 22), Person("wangwu", 25), Person("zhaoliu", 35))
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.map((_, 1))
    // 引入自定义分区器
    val rdd3 = rdd2.partitionBy(new MyPartitioner(2))
    val rdd4 = rdd3.partitionBy(new MyPartitioner(2))
    rdd4.saveAsTextFile("output")
  }

}
