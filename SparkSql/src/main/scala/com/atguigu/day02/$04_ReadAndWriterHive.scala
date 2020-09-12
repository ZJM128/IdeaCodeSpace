package com.atguigu.day02

import java.util.Properties

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SPARK_BRANCH, SparkConf}
import org.junit.{After, Before, Test}

/**
 * 数据以文件形式存在
 *    json
 *    CSV
 *    TSV
 *   数据在Mysql
 *   数据在Hive管理的HDFS上
 *
 *
 */
object $04_ReadAndWriterHive {

  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("my")
  private val sc: SparkSession = SparkSession.builder()
    .enableHiveSupport()// 启用Hive的支持
   .config("spark.sql.warehouse.dir", "hdfs://hadoop102:9820/user/hive/warehouse") //设置spark连接Hive写出数据的存储位置,默认是spark的安装目录
    .config(conf).getOrCreate()
  // 导入隐式类 注意不能导入非val的类型的变量
  import sc.implicits._

  @Before
  def start(): Unit ={
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path,true)
  }
  @After
  def stop(): Unit ={
    sc.close()
  }

  def main(args: Array[String]): Unit = {
    readHive()
    //writerHive()
  }
  /**
   * JDBC的读取
   */

  def readHive(): Unit ={

    // 创建数据库
    sc.sql("create database sparkHiveDB")
    // 展示hive所有的数据库
   sc.sql("show databases").show()
    // 使用数据库
    sc.sql("use sparkhivedb")
    // 创建表
   sc.sql("create table student(name string,age int)")
    // 插入数据
    sc.sql("insert into student values('lisi',23),('wangwu',34)")
    // 查询数据
   // sc.sql("select * from student").show()
    sc.close()

  }


  def writerHive(): Unit ={
    val list = List(Student3("lisi", 23), Student3("wangwu", 33))

    val rdd = sc.sparkContext.makeRDD(list,1)
    val ds = rdd.toDS()

    ds.write.mode("append")
      .saveAsTable("sparkHiveDB.student2")

    sc.close()
  }

}
case class Student3(var name:String,var age:Int)