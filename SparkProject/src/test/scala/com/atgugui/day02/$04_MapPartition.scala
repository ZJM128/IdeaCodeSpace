package com.atgugui.day02

import java.sql.Connection

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * (1)源码
 *
 * def mapPartitions[U: ClassTag](
 *    f: Iterator[T] => Iterator[U],
 *    preservesPartitioning: Boolean = false): RDD[U] = withScope {
 *    [1]当f存在闭包时,将闭包进行清理,确保使用的闭包变量可以序列化,才可以发给task
 *    val cleanedF = sc.clean(f)
 *
 *    new MapPartitionsRDD(
 *    this,
 *     [2]分区整体调用一次函数
 *    (_: TaskContext, _: Int, iter: Iterator[T]) => cleanedF(iter),
 *    preservesPartitioning)
 * }
 * (2)map和mapPartitions的区别
 *  [1]mapPartitions是批处理,按分区为单位调用函数,map是一对一的
 *  [2]某些场景下,只能使用mapPartitions,将一个分区的数据写入数据库中
 *  [3]map,只能对每个元素都进行map处理
 *      mapPartitions(批处理),可以对一个分区的数据进行任何类型的处理例如filter等其他算子都行！
 *    返回的记录可以和之前输入的记录个数不同！
 * (3)相同点
 *  [1]都返回迭代器
 *  [2]分区的个数不变
 *
 */

class $04_MapPartition {

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
   * mapPartition按照批处理,参数是整个集合,返回值也是一个集合,
   *  f: Iterator[T] => Iterator[U]
   */
  @Test
  def mapPartition(): Unit ={
    val list = List(1, 2, 3, 4, 5)
    val rdd = sparkContext.makeRDD(list,2)
    // mapPartition按照批处理,参数是一个集合,分区里面每个元素调用f函数
    val rdd2 = rdd.mapPartitions(list => list.filter(number => number > 3))
    rdd2.saveAsTextFile("output")

  }

  /**
   * mapPartition:可以减少对象的重复创建
   * 原因:有些类是不能序列化,也就是不能通过网络传输获得,因此需要重复创建
   */
  def mapPartition02():Unit={
   /* val rdd = sparkContext.makeRDD(List(1, 2, 4, 5))
    rdd.mapPartitions(partition=>{
      Connection
      partition.map(
      word=>Connection
    )}
    )*/
  }

  /**
   * 求出每个分区最大的值
   */
  @Test
  def mapPartitionTest02(): Unit ={
    val rdd = sparkContext.makeRDD(List(1, 2, 3, 4, 5, 6),2)
    val rdd2 = rdd.mapPartitions(iter => List(iter.max).iterator)
    rdd2.foreach(println)
  }



}
