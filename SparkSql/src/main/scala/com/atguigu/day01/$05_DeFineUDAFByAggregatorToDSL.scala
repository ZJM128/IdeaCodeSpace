package com.atguigu.day01

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoder, SparkSession,Encoders}
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.expressions.Aggregator
import org.junit.{After, Test}

class $05_DeFineUDAFByAggregatorToDSL {

  private val myConf: SparkConf = new SparkConf().setMaster("local").setAppName("my")
  private val sparkSession: SparkSession = SparkSession.builder().config(myConf).getOrCreate()

  import sparkSession.implicits._

  @After
  def stop(): Unit ={
    sparkSession.stop()
  }

  /**
   * 在DSL中使用Aggregator
   *select myAvg(salary) from emp 在 DSL
   *                  ①emp对应的应该有一个 df
   *                  ②  df.select(Column)
   *                       myAvg(salary) => Column
   *                       Aggregator.toColumn()
   *     DS[Employee]  ： ds
   * SQL 和 DSL的区别：输入的数据类型不同
   *        SQL:  myAvg(salary)salary是Employee的一个属性
   *        DSL：  ds.select(Column) 列的聚合函数的输入是整个 Employee对象， DS中的 T
   *        真正对T中的salary聚合是在聚合函数中自定定义
   */
    @Test
  def DSLToUDAF(): Unit ={
    val df = sparkSession.read.json("input/person.json")
    // 创建临时表
    //import sparkSession.implicits._ 隐式转换
    // ExpressionEncoder[Emp] 使用这个把类型隐式转换
    val ds = df.as[Emp](ExpressionEncoder[Emp])
    // 创建udaf函数
    val myAvg = new MyAvg2
    // 转换为Column类型
    val result = myAvg.toColumn
    // 查询聚集函数 .name给输出结果的列名起别名
    ds.select(result.name("avg")).show()
  }
}
/**
 * DSL中使用UDAF 的定义
 *
 * (1)问:为什么传入参数需要用样例类,而不是其他的基本类型
 *    答:因为使用DS的DSL语法传输入给函数的参数是有类型和结构的对象,因为需要根据DS的存储的数据格式
 *       来定义UDAF的传入参数
 * (2)问:为什么缓冲区的输入类型也是一个样例类
 *    答:这个跟业务有关,进行自主定义
 *
 *
 */
class MyAvg2 extends Aggregator[Emp, MyBuf1, Double] {
  // 清空缓冲区
  override def zero: MyBuf1 = new MyBuf1(0.0, 0)
  // 分区(缓冲区)内合并
  override def reduce(b: MyBuf1, a: Emp): MyBuf1 = {
    b.sum += a.age
    b.count += 1
    b
  }
  // 分区(缓冲间)间合并
  override def merge(b1: MyBuf1, b2: MyBuf1): MyBuf1 = {
    b1.count += b2.count
    b1.sum += b2.sum
    b1
  }
  // 所有的分区合并完成 返回结果
  override def finish(reduction: MyBuf1): Double = reduction.sum / reduction.count
  // 缓存区的Encoder类类型
  override def bufferEncoder: Encoder[MyBuf1] = Encoders.product
  // 输出的Encoder类型
  override def outputEncoder: Encoder[Double] =Encoders.scalaDouble
}

// 使用样例类
case class MyBuf1(var sum: Double, var count: Int)
case class Emp(name: String, age: Double)
