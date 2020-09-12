package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 1,函数签名
 * def sortByKey(ascending: Boolean = true, numPartitions: Int = self.partitions.length)
 * : RDD[(K, V)]
 * 2,函数说明
 * 在一个(K,V)的RDD上调用，K必须实现Ordered接口，返回一个按照key进行排序的
 *
 *
 */
class $08_SortBy {

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

  @Test
  def sortByKeyTest(): Unit ={
    val list = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("d", 98))
    val rdd = sc.makeRDD(list)
    // 默认按key的升序排序   改变ascending = false的值 为降序
    // 使用了隐式转换将当前的RDD(ParallelCollectionRDD)进行转换
    // ParallelCollectionRDD ===> 隐式转换 rddToOrderedRDDFunctions ==>   OrderedRDDFunctions
    val rdd1 = rdd.sortByKey(ascending = false ,numPartitions = 2)
    rdd1.saveAsTextFile("output")
  }

  /**
   * 自定义排序规则
   */
  @Test
  def sortByKeyTest01(): Unit ={
    val list = List(Student("jack", 20), Student("jack", 21), Student("marry", 21), Student("tom", 23))
    val rdd = sc.makeRDD(list, 2)
    // 按年龄进行排序
    // 想要那个值就行排序就将那个值设置成key
   // rdd.map(Student=>(Student.age,Student)).sortByKey().saveAsTextFile("output")

    // 都是降序
   /* rdd.map(Student=>((Student.name,Student.age),Student))
      .sortByKey(ascending = false,1)
      .saveAsTextFile("output")*/

    // 先按照年龄排序，年龄降序排序，年龄相同的，继续按照名称升序排序
    // 提供一个隐式的针对Person类型排序的Ordering类型的比较器

    implicit  val ordered =new Ordering[Student](){
      // 先按照年龄排序，年龄降序排序，年龄相同的，继续按照名称升序排序
      override def compare(x: Student, y: Student): Int = {
        var result = -x.age.compareTo(y.age)
        if (result==0)
          result=x.name.compareTo(y.name)
        result
      }
    }
    rdd.map(Student=>(Student,1))
      .sortByKey(numPartitions = 1)
      .saveAsTextFile("output")
  }

  /**
   * 扩展知识
   */
  implicit  var i :Int =10
  // implicit  var j :Int =10
  /*
        冥界(隐式)召唤
   */
  @Test
  def testImplicitly() : Unit ={

    //使用冥界召唤，从当前方法的作用域中，获取指定类型的隐式变量
    val i1: Int = implicitly[Int]
    println(i1)

  }
}
case class Student( name:String, age:Int)