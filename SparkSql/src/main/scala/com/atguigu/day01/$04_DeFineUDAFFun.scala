package com.atguigu.day01

import org.apache.parquet.filter2.predicate.Operators.UserDefined
import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.{Encoder, Encoders, Row, SparkSession, functions}
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, StructField, StructType}
import org.junit.{After, Test}

/**
 * 自定义UDAF函数
 * (1)spark3.0 推荐使用Aggregator (强类型)
 * (2)spark2.0 aggregator 和UserDefinedAggregateFunction(弱类型)
 * 容易发生类型转换异常
 *
 * 问:什么是UDAF函数
 * 答:多进一出,因为要等到所有的行的一列数据全部运算完成,才把结果返回,所以UDAF函数
 * 一定用到内存的缓存,所以UDAF也会引起内存的溢出
 *
 * 问:为什么说UserDefinedAggregateFunction是一个弱类型
 * 答:继承UserDefinedAggregateFunction的子类不需要定义传参的类型
 *
 * (3)Aggregator 和 UserDefinedAggregateFunction的区别
 *    [1]aggregator是spark3.0 推出的 属于强类型 继承aggregator时需要确定输入类型 缓存区类型 和输出类型
 *       UserDefinedAggregateFunction是spark2.0的属于弱类型,继承的时候不用指定强制指定类型,要在属性中指定
 *       输入,缓冲区,输出的类型
 *    [2]方法中注册UDAF函数的时候,
 *    ①aggregator(强类型)
 *       Ⅰ:DF中调用自定义UDAF
 *        sparkSession.udf.register("myAvg", functions.udaf(myAvg))
 *
 *       Ⅱ:DS中调用自定义UDAF
 *        问:为什么要调用toColumn 答:因为DSL的传入的是整个对象
 *         val result = myAvg.toColumn
 *    ② UserDefinedAggregateFunction
 *          sparkSession.udf.register("mySum", mySum)
 *
 * (4)SQL 和 DSL的区别：输入的数据类型不同
 *        SQL:  myAvg(salary)salary是Employee的一个属性
 *         DSL：  ds.select(Column) 列的聚合函数的输入是整个 Employee对象， DS中的 T
 *         真正对T中的salary聚合是在聚合函数中自定定义
 * (5)类功能的横向扩展
 *  import sparkSession.implicits._ 隐式转换
 *   ExpressionEncoder[Emp] 通过ExpressionEncoder把Emp拥有其他类的功能
 *      eg:val ds = df.as[Emp](ExpressionEncoder[Emp])
 */
class $04_DeFineUDAFFun {

  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("aa")
  private val sparkSession: SparkSession = SparkSession.builder().config(conf).getOrCreate()

  // 引用隐式转换

  //import sparkSession.implicits._

  @After
  def stop(): Unit = {
    sparkSession.stop()
  }

  @Test
  def testUserDefinedAggregateFunction(): Unit = {
    // 1 创建UDAF 函数
    val mySum = new MySum
    // 2注册函数
    sparkSession.udf.register("mySum", mySum)
    // 3使用UDAF 函数
    val df = sparkSession.read.json("input/person.json")
    // 创建临时表
    df.createTempView("temp")
    // 4 调用UDAF函数
    sparkSession.sql("select mySum(age) from temp").show()

  }

  /**
   * 使用Aggregator声明UDAF函数
   * 注册函数的时候有区别
   * sparkSession.udf.register("myAvg",functions.udaf(myAvg))
   */
  @Test
  def testAggregator(): Unit = {
    val myAvg = new MyAvg
    // 创建函数 注册函数
    val myAvg1 = sparkSession.udf.register("myAvg", functions.udaf(myAvg))
    val df = sparkSession.read.json("input/person.json")
    // 创建临时表
    //df.createTempView("temp")
    // 4 在sql语句中调用UDAF函数
   //sparkSession.sql("select myAvg(age) from temp").show()
  }

}

/**
 * 使用UserDefinedAggregateFunction 定义UDAF函数
 * 问:为什么说UserDefinedAggregateFunction是一个弱类型
 * 答:继承UserDefinedAggregateFunction的子类不需要定义传参的类型
 */
class MySum extends UserDefinedAggregateFunction {
  // 输入的数据的类型
  override def inputSchema: StructType = StructType(StructField("sal", DoubleType) :: Nil)

  // 缓冲区的类型
  override def bufferSchema: StructType = StructType(StructField("sal", DoubleType) :: Nil)

  // 最终运算结束后结果的类型
  override def dataType: DataType = DoubleType

  // 是否是一个确定性(相同输入一定返回相同的输出)的函数
  override def deterministic: Boolean = true

  // 初始化缓冲区 MutableAggregationBuffer 看做Array
  override def initialize(buffer: MutableAggregationBuffer): Unit = buffer(0) = 0.0

  // 分区内合并 将每一行 指定列的值,聚合到缓冲区
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    // 从输入中取出数据和缓冲区中的数据累加,累加后将结果再放入缓冲区中
    buffer(0) = buffer.getDouble(0) + input.getDouble(0)
  }

  // 分区间合并 将不同分区合并后的buffer再进行合并,将buffer中的值累加,将buffer分放入buffer1
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    // 将分区间的buffer尽心累加
    buffer1(0) = buffer1.getDouble(0) + buffer2.getDouble(0)
  }

  // 返回结果,从缓冲区取出聚合后的结果
  override def evaluate(buffer: Row): Any = buffer.getDouble(0)
}




/**
 *
 * (1)Aggregator的构造器: Aggregator[-IN, BUF, OUT]
 * (2)Aggregator构造器参数的说明
 * [1]第一个参数:输入的类型
 * [2]第二个:缓冲区的类型
 * [3]第三个:输出的类型 计算逻辑需要在缓冲区保存什么样的值
 * 如果中间保存的数据很多,建议使用样例类来保存
 *
 *
 */
class MyAvg extends Aggregator[Double, MyBuf, Double] {
  //  初始化缓冲区 ,
  //  问:为什么要初始化缓冲区 答:因为可能之前UDAF函数使用过缓冲区
  override def zero: MyBuf = new MyBuf(0.0, 0)

  // 分区内合并
  override def reduce(b: MyBuf, a: Double): MyBuf = {
    b.sum += a
    b.count += 1
    b
  }
  // 分区间合并
  override def merge(b1: MyBuf, b2: MyBuf): MyBuf = {
    b1.sum += b2.sum
    b1.count += b2.count
    b1
  }
  // 全部分区间合并完成返回结果
  override def finish(reduction: MyBuf): Double = reduction.sum / reduction.count

  // 缓冲区的Encoder 固定写法
  override def bufferEncoder: Encoder[MyBuf] = Encoders.product

  // 输出类型的Encoder
  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

// 使用样例类
case class MyBuf(var sum: Double, var count: Int)




