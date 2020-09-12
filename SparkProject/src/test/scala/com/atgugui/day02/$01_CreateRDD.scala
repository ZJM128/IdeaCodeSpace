package com.atgugui.day02

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit._

/**
 *test下读取的相对路径 相对于module
 */
class $01_CreateRDD {

  var context:SparkContext=new SparkContext(new SparkConf().setMaster("local").setAppName("mySpark"))

  @Before
  def start():Unit={
    // 删除目录
    val system:FileSystem = FileSystem.get(new Configuration())
    val path:Path = new Path("output")
    if (system.exists(path)){
      system.delete(path,true)
    }
  }

  /**
   * 关闭资源
   */
  @After
  def close()={
    context.stop()
  }
  @Test
  def test01()={
    val rdd = context.makeRDD(List(1, 2, 3, 4, 5))
    // 对结果进行输出
    rdd.saveAsTextFile("output")
  }

  /**
   * 1)	从集合（内存）中创建RDD
   */
    @Test
  def testMakeRDD(): Unit ={
      // makeRDD的底层是 parallelize(seq, numSlices)
      val rdd = context.makeRDD(List(2, 4, 5, 6, 7))
      // makeRDD等价于parallelize
      val rdd2 = context.parallelize(List(2, 3, 5, 6, 7, 8))
      rdd.saveAsTextFile("output")
      rdd2.saveAsTextFile("output2")

  }

  /**
   * 	从外部存储（文件）创建RDD
   */
    @Test
  def testFile()={
    val textRDD = context.textFile("input")
    textRDD.saveAsTextFile("outputText")
  }

  /**
   * 从其他RDD创建
   */
    @Test
  def testFromOtherRDD()={
      val textRDD = context.textFile("input")
      // 从上一个RDD生成另一个RDD
      val rdd1 = textRDD.flatMap(line => line.split(" "))
      val rdd2 = rdd1.map(word => (word, 1))
      val rdd3 = rdd2.reduceByKey((value1,value2)=>value1+value2)
      //println(rdd3.collect().mkString(","))
      rdd3.saveAsTextFile("otherOutput")
  }



}
