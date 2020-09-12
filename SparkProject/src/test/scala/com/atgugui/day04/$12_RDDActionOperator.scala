package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{RangePartitioner, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 1,行动算子和转换算子的区别
 *    转换算子将一个RDD转换为另一个RDD,是懒执行
 *     行动算子,是提交job
 * 2,reduce:提供一个函数,将RDD中元素两两运算
 * 3,collect 将RDD中的元素,以Array的形式收集到Driver端
 *          只有当RDD中的元素数据比较少的时候,才可以使用collect方法
 *          不然可能是driver端的内存溢出
 * 4,count 返回rdd中元素的个数
 * 5,first:返回RDD中的第一个元素,第一个分区的第一条数据
 * 6,take 按照分区依次取出前n条数据
 * 7,takeOrdered:先排序,之后依次取出前n个元素
 *      take和takeOrdered都需要注意 取的数据不能太多,否则diver端会
 *      出现OOM错误
 * 8,aggregate 和aggregateByKey的区别,在于zeroValue不仅会在分区内参与运算
 *              在分区间也会参与了运算
 * 9,fold:fold是aggregate的简化版,如果分区内和分区间的运算逻辑一致,可以简化使用fold
 * 10,countByKey:必须是key-value类型的rdd才能使用,统计形同的key对应的k-v的个数
 * 11,countByValue:统计相同元素的个数
 * 12,save相关
 * 13,foreach:在特殊的场景下,使用效率低,将RDD中每个的元素写入数据库
 *    建议使用foreachPartition,一个分区作为一批数据进行处理
 *    map--mapPartition
 */
class $12_RDDActionOperator {
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
   * rdd 运行在executor上,直接操作rdd中的元素,也就是在executor上操作
   */
  @Test
  def foreachTest(): Unit ={
    val list = List(11, 2, 3, 4,5,5,5,5)
    // 本地模式以多线程的形式模拟多个Task执行
    val rdd= sc.makeRDD(list, 3)
    //无序   在Executor上，分布式打印
    rdd.foreach(println)
    //有序 在Driver执行foreach
    rdd.collect().foreach(println)
  }

  @Test
  def test01()={
    val list = List(11, 2, 3, 4,5,5,5,5)
    val list2 = List((11,1), (2,1),(5,1),(5,2),(5,3))

    val rdd = sc.makeRDD(list, 3)
    val rdd2 = sc.makeRDD(list2, 3)

    //返回rdd中元素的个数
    //println(rdd.count())

    //  5,first:返回RDD中的第一个元素,第一个分区的第一条数据
    //println(rdd2.first())

   //6,take 按照分区依次取出前n条数据,会跨区取数据
    //println(rdd2.take(3).mkString(","))

   //  7,takeOrdered:先排序,之后依次取出前n个元素 默认是升序
   //take和takeOrdered都需要注意 取的数据不能太多,否则diver端会
   //出现OOM错误
    // println(rdd2.takeOrdered(3).mkString(","))

   //8,aggregate 和aggregateByKey的区别,在于zeroValue不仅会在分区内参与运算
   //在分区间也会参与了运算
   // println(rdd.aggregate(10)(_ + _, _ + _))  --80

   //9,fold:fold是aggregate的简化版,如果分区内和分区间的运算逻辑一致,可以简化使用fold
    //println(rdd.fold(10)(_ + _)) --80

   //10,countByKey:必须是key-value类型的rdd才能使用,统计相同的key对应的k-v的个数
    //val rdd3 = rdd.map((_, 1))
    // 5 -> 4,2 -> 1,3 -> 1,11 -> 1,4 -> 1
    // println(rdd3.countByKey().mkString(","))
    //println(rdd2.countByKey().mkString(","))

   //11,countByValue:统计相同元素的个数 可以数单value也可以是k-v,
    // 单value的时候是统计相同value的个数,k-v是统计相同k-v的个数
    // 5 -> 4,2 -> 1,3 -> 1,11 -> 1,4 -> 1
    //println(rdd.countByValue().mkString(","))
    // (5,2) -> 1,(11,1) -> 1,(5,1) -> 1,(5,3) -> 1,(2,1) -> 1
    //println(rdd2.countByValue().mkString(","))

    // 求总和
   // println(rdd.reduce(_ + _))

   //12,save相关 序列化为了网络传输和减少空间
    //rdd2.saveAsTextFile("output")
    // 序列化
    //rdd2.saveAsObjectFile("output1")
    // 序列化后的k-v 只有k-v类型才能调用
    //rdd2.saveAsSequenceFile("output2")
   //13,foreach:在特殊的场景下,使用效率低,将RDD中每个的元素写入数据库
    rdd.foreach(println)
   //建议使用foreachPartition,一个分区作为一批数据进行处理
   /* 11,2
    3,4,5
    5,5,5*/
    rdd.foreachPartition(x=> println(x.mkString(",")))
  }

  /**
   * 特列,sortBy RDD也当做可以行动算子
   *sortBy底层调用了RangePartitioner
   * 而是创建RangePartitioner时，需要生成边界数组，因此调用了collect()提交了Job
   */
  @Test
  def test02()={

    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list, 2)
    val rdd1 = rdd.map(x => (x, 1))

    rdd1.checkpoint()
    //val rdd3 = rdd.sortBy(x => x)
    val value = new RangePartitioner(3, rdd1)
    Thread.sleep(100000)

  }
}
