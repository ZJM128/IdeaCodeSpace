package com.atguigu.day01

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.junit.{After, Test}
/**
 * 定义UDF函数
 * 问:为什么要定义UDF函数,有什么用?
 * 答:UDF是一进一出的函数,一般用于处理一对一的数据,类似map或foreach
 * 问:如何定义?
 * 答:(1)定义函数 也可以使用匿名函数
 *    (2)注册UDF函数  sparkSession.udf.register("addAge",ageAdd _)
 *    (3)在sparkSql中使用UDF函数
 *
 * 注意:UDF函数必须要有返回值
 *
 */
class $03_DefineUDFFun {

  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("aa")
  private val sparkSession: SparkSession = SparkSession.builder().config(conf).getOrCreate()

  // 引用隐式转换
  import sparkSession.implicits._
  @After
  def stop(): Unit ={
    sparkSession.stop()
  }


    @Test
  def creataUDFFun(): Unit ={
    // 定义UDF函数
    def ageAdd(age:Int): Int ={
      age+10
    }
    val list = List(person("li", 23), person("zhangsan", 34))
    val rdd = sparkSession.sparkContext.makeRDD(list)
    val df = rdd.toDF("name", "age")
    // 创建临时表
    df.createTempView("temp")
    // 注册UDF函数
    val addAge1 = sparkSession.udf.register("addAge", ageAdd _)
    // 使用UDF函数
    sparkSession.sql("select addAge(age) from temp").show()
      // 在dsl中使用UDF函数
      df.select(addAge1('age))
  }
}
case class person(var name:String,var age:Int)