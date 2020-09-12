package com.atguigu.wordCount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Test}
import scala.collection.mutable

/**
 * Spark之14种WordCount
 */
class WordCountList {

  private val sparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("MySpark"))

  private val rdd: RDD[String] = sparkContext.makeRDD(List("good spark are you ok", "scala is best", "scala is spark base"))

  @After
  def stop(): Unit = {
    sparkContext.stop()
  }

  /**
   * 第一种
   * flatMap+groupBy+map
   * (1)使用flatMap将每行映射扁平化成一个个单词
   * (2)使用groupBy将每个单词进行分组,得到(单词,iter(单词,单词,....))
   * (3)使用map 将groupBy得到的结果转换成(单词,次数)
   */
  @Test
  def wordCountTest01(): Unit = {
    rdd.flatMap(line => line.split(" "))
      .groupBy(word => word)
      .map { case (word, iter) => (word, iter.size) }
      .foreach(println)
  }

  /**
   * 第二种
   * reduceByKey
   * (1)使用reduceByKey 的关键是元素是k-v类型的
   * reduceByKey的作用是将相同的key的value进行操作
   * (2)此处需要对每个单词转化为(单词,1) 出现一次
   */
  @Test
  def wordCountTest02(): Unit = {
    rdd.flatMap(line => line.split(" ").map(word => (word, 1)))
      .reduceByKey(_ + _)
      .foreach(println)

  }

  /**
   * 第三种
   * aggregateByKey
   * (1)aggregateByKey的作用:可以设置(默认值)(分区内合并,分区间合并)
   * (2)分区内和分区间的逻辑可以不一样
   * (3)此处默认值为0,分区内的逻辑和分区间的逻辑一样
   */
  @Test
  def wordCountTest03(): Unit = {
    rdd.flatMap(_.split(" ").map(word => (word, 1)))
      .aggregateByKey(0)(_ + _, _ + _)
      .foreach(println)
  }

  /**
   * foldByKey
   * 1,用法:foldByKey是aggregateByKey的简写
   *        foldByKey代表分区内和分区间的逻辑一样(默认值)(分区内和分区间的逻辑)
   * 2,此处默认值为0,分区内的逻辑和分区间的逻辑一样
   */
  @Test
  def wordCountTest04(): Unit = {
    rdd.flatMap(_.split(" ").map(word => (word, 1)))
      .foldByKey(0)(_ + _)
      .foreach(println)
  }

  /**
   * 第五种
   * combinerByKey
   * 1,用法:combinerByKey是aggregateByKey的升级版本
   *   默认值可以转换成另一种类型,然后参与分区内逻辑的计算
   * 2,combinerByKey的参数(默认值,分区内逻辑,分区间逻辑)
   * 3,注意点:combinerByKey分区内和分区间的参数都需要带上类型,不能自动推断
   *   问:为什么不能自动推断
   *   答:因为默认值的类型是可以转变的,后面的参数没法跟着变,所以需要用户自定义
   */
  @Test
  def wordCountTest05(): Unit = {
    rdd.flatMap(_.split(" ").map(word => (word, 1)))
      .combineByKey(x=>x,(x:Int,y:Int)=>x+y,(x:Int,y:Int)=>x+y)
      .foreach(println)
  }

  /**
   * 第六种
   * groupByKey
   * (1)作用:根据相同的key值进行分组 得到(word,iter(1,1,1,.....))
   * (2)和groupBy的区别在于:groupByKey是统计k-v,而groupBy统计单value类型的
   * (3)和groupBy的相同点:两者的功能一样
   */
  @Test
  def wordCountTest06(): Unit = {
    rdd.flatMap(_.split(" ").map(word => (word, 1)))
      .groupByKey()
      .map{
        case (word,iter)=>(word,iter.size)
      }
      .foreach(println)
  }

  /**
   * 第七种
   * countByKey
   * (1)使用场景:当数据量比较少的时候使用,因为countByKey会把所有数据汇总到Driver端
   * 进行统计
   * (2)作用:根据Key值进行count(1)
   */
  @Test
  def wordCountTest07(): Unit = {
      rdd.flatMap(_.split(" ").map(word => (word, 1)))
      .countByKey()
      .foreach(println)
  }

  /**
   * 第八种
   * countByValue
   * (1)作用:根据value进行count() 所以需要把需要统计的单词转为value
   */
  @Test
  def wordCountTest08(): Unit = {
    rdd.flatMap(_.split(" "))
      .map((1,_))
      .countByValue()
      .map{
        case ((num,key),total)=>(key,total)
      }.foreach(println)
  }

  /**
   * 第九种
   * cogroup
   * (1)cogroup 合并两个RDD相同的key,先把每个RDD相同的key合并=>(key,iter)
   *    然后再把两个RDD相同的key合并=>(key,(iter1,iter2),如果有一个key一方有
   *    而另一方没有,两个RDD合并的时候则没有的一方会用CompactBuffer()代替
   * (2)此处借助了
   */
  @Test
  def wordCountTest09(): Unit = {
    val rdd2 = sparkContext.makeRDD(List(("a", 0)))
    rdd.flatMap(_.split(" "))
      .map((_,1))
      .cogroup(rdd2)
      .map{
        case (word,(iter1,_))=>(word,iter1.size)
      }.foreach(println)
  }

  /**
   * mapValues
   * (1)面向的是k-v的RDD
   * (2)作用:对k-v的RDD的v进行操作
   * (3)此处利用分组后的结果(key,iter)
   *    通过mapValues把iter类型的v转为Int类型的V
   */
  @Test
  def wordCountTest10(): Unit = {
    rdd.flatMap(_.split(" "))
      .groupBy(word=>word)
      .mapValues(_.size)
      .foreach(println)
  }

  /**
   *
   * 第十一中
   * reduce + foldLeft
   * foldLeft说明 : def foldLeft[B](z: B)(op: (B, A) => B): B
   * (1)柯里化参数:第一个参数列表一个参数是初始值(就是两个数相加,总要有一个数是给定的),
   *    第二个参数列表有两个参数:第一个是累加后的值,第二个参数是遍历传入的值
   * (2)在此处利用了map的特性,根据key来判断之前是否有存在过(累加过),没有就赋值为0
   * 再和传进来的值相加,有的话取出值和传进来的值进行相加
   *
   */
  @Test
  def wordCountTest11(): Unit = {
    rdd.flatMap(_.split(" "))
      .map(word=>Map[String,Int]((word,1)))
      .reduce((map1,map2)=>{
        map1.foldLeft(map2)((map,kv)=>{
          val key = kv._1
          val count = kv._2
         map.updated(key,map.getOrElse(key,0)+count)
        })
      }).foreach(println)
  }

  /**
   *
   * 第十二种
   *   aggregate
   * (1)函数说明:def aggregate[U: ClassTag](zeroValue: U)(seqOp: (U, T) => U, combOp: (U, U) => U): U
   *    [1]柯里化第一个参数列表零值,参与分区内的和分区间的运算
   *    [2]第二个参数列表第一个参数:分区内的逻辑 (U,T)=>U 第一个类型和零值的类型一样,第二个不要求,但返回值要求和零值一样
   *       第二个参数:分区间的逻辑(U,U)=>U 两个类型要和零值的类型一样,返回值也要一致
   */
  @Test
  def wordCountTest12(): Unit = {
    rdd.flatMap(_.split(" "))
      .map((_,1))
      .aggregate(mutable.Map[String,Int]())((map,kv)=>{
        val key = kv._1
        val value = kv._2
        map.put(key,map.getOrElse(key,0)+value)
        map
      },(map,map1)=>{
        map.foldLeft(map1)((map1,kv1)=>{
          val key = kv1._1
          val value = kv1._2
          map1.put(key,map1.getOrElse(key,0)+value)
          map1
        })
      }).foreach(println)

  }

  /**
   * fold是aggregate的简写版
   * (1)当aggregate的分区内和分区间的逻辑一样的时候,可以使用fold代替
   */
  @Test
  def wordCountTest13(): Unit = {
    rdd.flatMap(_.split(" "))
      .map(word=>mutable.Map[String,Int]((word,1)))
      .fold(mutable.Map[String,Int]())((map1, map2)=>{
        map1.foldLeft(map2)((result,kv)=>{
            val key = kv._1
            val value = kv._2
            result.put(key,result.getOrElse(key,0)+value)
            result
        })
      }).foreach(println)
  }
}
