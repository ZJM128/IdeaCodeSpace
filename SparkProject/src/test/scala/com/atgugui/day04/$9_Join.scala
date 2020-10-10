package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 所有的Join都有shuffle
 * Join:
 * leftJoin
 * rightjoin
 * full join
 *
 * 所有的join都有shuffle,针对的是key
 */
class $9_Join {

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
  def testJoin(): Unit ={
/*    val list1 = List(("a", 88), ("b", 95),("c",20),("b",93))
    val list2 = List(("a", 2), ("b", 2),("d",30),("a",30))*/
    val list1 = List(("a", 1), ("a", 1))
    val list2 = List(("a", 2), ("a", 2))

    val rdd1 = sc.makeRDD(list1)
    val rdd2= sc.makeRDD(list2)
    // 取的是交集
    rdd1.join(rdd2).foreach(println)

    // 取左边的全部 和右边有关联的元素,右边没有的用None代替
    rdd2.leftOuterJoin(rdd1).saveAsTextFile("output")

    // 取右边的全部 和左边有关联的元素,左边没有的用None代替
    //rdd2.rightOuterJoin(rdd1).saveAsTextFile("output")

    // 全左右两边的全部,没有关联的用None表示
    //rdd2.fullOuterJoin(rdd1).saveAsTextFile("output")

  }

}
