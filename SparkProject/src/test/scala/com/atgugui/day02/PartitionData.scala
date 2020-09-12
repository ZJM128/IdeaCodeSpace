package com.atgugui.day02

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

class PartitionData {

  @Test
  def test01()={
    //6个字节 2个分区=>分片的大小为3个字节==>6/3=2  ==>分2个分区 (0,3),(3-6)
    // 数据是1@@
    //      234
    val sparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("my_park"))
    val rdd = sparkContext.textFile("input",3)
    rdd.saveAsTextFile("output")
    sparkContext.stop()
  }
}
