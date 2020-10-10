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
 *
 *
 *
 *
 */
class $03_ReadAndWriterJDBC {

  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("my")
  private val sc: SparkSession = SparkSession.builder().config(conf).getOrCreate()
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

  /**
   * JDBC的读取
   */
  @Test
  def readJDBC(): Unit ={

    // 配置jdbc的信息,用户,密码等
    val properties = new Properties()
    properties.put("user","root")
    properties.put("password","root")

    // jdbc()有三个参数(1)配置jdbc的连接 (2)要查询的表 (3)数据库的账号信息
    val df = sc.read.jdbc("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "user01", properties)
    //df.show()

    // 创建临时表 用于后续的使用
    df.createTempView("user")

    // 可以对表进行后续的操作
    sc.sql("select * from user").show()

  }

  @Test
  def writerJDBC(): Unit ={
    val list = List(Student2("lisi", 23), Student2("wangwu", 33))

    val rdd = sc.sparkContext.makeRDD(list,1)
    val ds = rdd.toDS()

    // 配置jdbc的信息
    val properties = new Properties()
    // key是固定写法
    properties.put("user","root")
    properties.put("password","root")

    /*1,写出数据的模式
      (1)默认的写出:SaveMode.ErrorIfExists
            如果写出路径存在,就报错
      (2)overwrite:覆盖写
      (3)append:追加写,向输出目的目录追加文件
      (4)ignore:忽略本次写出*/
    ds.write.mode("append")
      .jdbc("jdbc:mysql://localhost:3306/test?serverTimezone=UTC","user01",properties)


  }

}
case class Student2(var name:String,var age:Int)