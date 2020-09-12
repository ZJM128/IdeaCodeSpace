package com.atguigu.day01

import org.apache.spark.{SparkConf, SparkContext}

object wordCount {
  def main(args: Array[String]): Unit = {
    // 1设置配置文件
    val conf = new SparkConf().setMaster("local").setAppName("mySpark")
    // 2 手动创建应用的上下文
    val SparkContext = new SparkContext(conf)

    // 3 创建RDD 读取的是文件中每一行的数据
    val rdd = SparkContext.textFile("input")
    // 4 对每一行数据进行处理,分割,压平,形成一个一个单词
    val rdd1 = rdd.flatMap(line => {
      line.split(" ")
    })
    // 5 对rdd1中每一个单词进行赋1 操作
    val rdd2 = rdd1.map(word => (word, 1))

    // 6 对rdd2进行分组 合计 =>reduceByKey方法自动把集合的元素进行分组
    val resultRDD = rdd2.reduceByKey((value1, value2) => value1 + value2)

    // 7 获取结果 进行输出
    println(resultRDD.collect().mkString(","))
    // 运行完关闭sparkContext
    SparkContext.stop()

  }
}
