package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 1,函数签名:def combineByKey[C](
 * createCombiner: V => C,
 * mergeValue: (C, V) => C,
 * mergeCombiners: (C, C) => C): RDD[(K, C)]
 *
 * 	函数说明
 * 最通用的对key-value型rdd进行聚集操作的聚集函数
 * （aggregation function）。类似于aggregate()，
 * combineByKey()允许用户返回值的类型与输入不一致
 *
 * 2,形参： 相同key内进行操作
 *      参数1：createCombiner: V => C,表示将计算的第一个值进行结构转化
 *      形参：相同key组内的第一个value元素
 *      返回：value经过转换后的数据
 *      参数2：mergeValue: (C, V) => C,表示分区内的计算规则
 *      形参：参数1为经过处理后value，参数2为组内的一个一个的value
 *      返回：value经过处理后的数据
 *      参数3：mergeCombiners: (C, C) => C)：表示分区间的计算规则
 *      形参：相同key，两个经过分区内处理过的v
 *      返回：返回两个v的处理结果
 *
 *
 *  3,reduceByKey、foldByKey、aggregateByKey、combineByKey的区别
 *  从源码的角度来讲，四个算子的底层逻辑是相同的。
 * ReduceByKey不会对第一个value进行处理，分区内和分区间计算规则相同。
 * AggregateByKey的算子会将初始值和第一个value使用分区内计算规则进行计算。
 * FoldByKey的算子的分区内和分区间的计算规则相同，初始值和第一个value使用分区内计算规则。
 * CombineByKey的第一个参数就是对第一个value进行处理，所以无需初始值
 *
 */
class $07_CombineByKey {

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
  def combineByKeyTest01(): Unit = {

    val list = List((1, 1), (1, 2), (2, 1), (1, 3), (2, 2), (2, 2))
    val rdd = sc.makeRDD(list, 2)
    val rdd2 = rdd.aggregateByKey(10)(_ + _, _ + _)
    //等价于
    val rdd3 = rdd.combineByKey(v => v + 10, (zero: Int, v) => zero + v, (v1: Int, v2: Int) => v1 + v2)

    rdd2.saveAsTextFile("output")
    rdd3.saveAsTextFile("output1")
  }

  /**
   * 将数据List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))求每个key的平均值
   */
  @Test
  def combineByKey02(): Unit = {
    val list = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))
    sc.makeRDD(list).combineByKey(value=>(value,1),
      (Tuple2:(Int,Int),value)=>(Tuple2._1+value,Tuple2._2+1),
      (tuple:(Int,Int),tuple1:(Int,Int))=>(tuple._1+tuple1._1,tuple._2+tuple1._2)
    ).map{case (key,(sum,count))=>(key,sum.toDouble/count)}
      .saveAsTextFile("output")
  }
}
