package com.atguigu.day01

import org.apache.spark.{SparkConf, SparkContext}

object WordCount1 {
  def main(args: Array[String]): Unit = {

    // 配置spark的运行信息 本地模式运行,运行任务的名称
    val conf = new SparkConf().setMaster("local").setAppName("mySpark")
    // 创建Spark的上下文对象
    val sparkContext = new SparkContext(conf)
    // 创建RDD对对象 关联对输入路径 创建 textFile默认是读取文件的每一行
    val rdd = sparkContext.textFile("hdfs://hadoop102:9820/input")
    // 对每一行数据进行分割,压平处理
    val rdd1 = rdd.flatMap(line => line.split(" "))
    // 对每个单词进行重复赋值,列裁剪
    val rdd2 = rdd1.map(word => (word, 1))
    // 统计
    val rdd3 = rdd2.reduceByKey((value1, value2) => value1 + value2)
    // 获取数据
    // println(rdd3.collect().mkString(","))
    rdd3.foreach(println)
    // 关闭资源
    sparkContext.stop()
  }
}
