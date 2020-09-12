package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before}

/**
 * def pipe(command: String): RDD[String]
 * 	函数说明
 * 管道，针对每个分区，都调用一次shell脚本，返回输出的RDD。
 * 注意："shell脚本需要放在计算节点可以访问到的位置"
 *
 */
class $09_Pipe {

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

  def pipeTest(): Unit ={

  }
}
