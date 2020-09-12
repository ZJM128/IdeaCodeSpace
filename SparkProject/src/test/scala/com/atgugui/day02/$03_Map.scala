package com.atgugui.day02

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 1,源码:
 *  def map[U: ClassTag](f: T => U): RDD[U] = withScope {
 *    val cleanF = sc.clean(f)
 *    new MapPartitionsRDD[U, T](this, (_, _, iter) => iter.map(cleanF))
 *  }
 * 2,总结:
 * (1)当f存在闭包时,将闭包进行清理,确保使用的闭包变量可以序列化,才可以发给task
 * val cleanF = sc.clean(f) ===>
 * def clean(
 *    closure: AnyRef,
 *    checkSerializable: Boolean = true,
 *    cleanTransitively: Boolean = true): Unit = {
 *    clean(closure, checkSerializable, cleanTransitively, Map.empty)
 * }
 *(2)iter调用scala的map方法,迭代集合中的元素,每个元素调用f
 *
 * 特点:
 * (1)底层实现是MapPartitionsRDD
 * (2)不会改变之前RDD的分区数,也不会改变元素的分区
 * (3)面向每个元素,输入一个元素就输出一个元素
 */

class $03_Map {

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
   *RDD的map方法和scala中的方法功能一样
   * 都是一对一的操作
   */
  @Test
  def mapTest(): Unit ={
    val list=List(1,2,3,4)
    val rdd = sparkContext.makeRDD(list)
    val rdd1 = rdd.map(_ * 2)
    rdd1.foreach(println)
  }

  /**
   * 闭包变量需要实现Serializable接口
   */
  @Test
  def mapTest01:Unit={
    // 声明局部变量
    val number=1
    val rdd = sparkContext.makeRDD(List(1, 2, 3, 4, 5))
    // 把局部变量通过ApplicationMaster传给executor进行计算,要求number变量是可以序列化的
    // 进而形成了闭包,所以number变量要求实现Serializable接口
    val rdd1 = rdd.map(_ + number)
    rdd1.saveAsTextFile("output")

  }

  /**
   * RDD的map方法的小练习
   */
  @Test
  def mapTest02(): Unit ={
    // 1创建文件RDD
    val rdd = sparkContext.textFile("input/apache.log")
    // 调用map对文件进行切分并返回索引为6的数据
    val rdd1 = rdd.map(_.split(" ")(6))
    // 输出到文件中
    rdd1.saveAsTextFile("output")



  }

  /**
   * a、分区内数据按照顺序依次执行，且第一条数据的所有逻辑执行完成以后再执行第二条数据，依次类推
   * b、分区间的数据执行是没有顺序，而且无需等待，即分区间执行逻辑互不影响，各自执行各自的逻辑
   */
  @Test
  def test03():Unit={
    val rdd = sparkContext.makeRDD(List(1, 2, 3, 4), 2)
    val rdd1 = rdd.map(x=>{
      println("MapA=>"+x)
      x
    })

    val rdd2 = rdd1.map(x=>{
      println("MapB=>"+x)
      x
    })
    println(rdd2.collect().mkString(","))

  }


}
