package com.atguigu.day01

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.junit.{After, Test}


class DataFrameAndDataSet {

  // 创建sparkSql的环境
  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("mySpark")
  // 创建sparkSession的环境 需要依赖sparkConf的环境
  private val sparkSession: SparkSession = SparkSession.builder().config(conf).getOrCreate()

  import sparkSession.implicits._

  @After
  def stop(): Unit = {
    sparkSession.stop()
  }

  /**
   * 创建DataFrame
   */
  @Test
  def testCreateDF(): Unit = {
    val df = sparkSession.read.json("input/person.json")
    // 默认显示前20行
    //df.show()
    // 创建临时变量表
    df.createTempView("emp")
    // 查询临时表
    // 问:为什么要使用sparkSession来调用sql方法来执行sql语句 而不是df
    // 答:df是RDD的封装和扩展,将数据增加了结构信息  而
    // sparkSession相当于mysql的运行环境 emp就是一张那视图表,

    //
    // 按条件查询
    //sparkSession.sql("select * from emp where age>20").show()

    // 使用mysql的函数查询
    //sparkSession.sql("select max(age) from emp ").show()

    // 使用DSL语句
    //df.select("name").show()
    df.agg(Map("age" -> "count")).show()
  }

  /**
   *
   * 问:如何使rdd拥有toDF或toDS的功能
   * 答:使用类型的隐式转换,把RDD类型的转换为rddToDatasetHolder
   * 通过rddToDatasetHolder中的DatasetHolder(_sqlContext.createDataset(rdd))
   * 方法把DatasetHolder中的方法扩展给RDD
   * 总结:这种方法遵循了开闭原则,是比较强大的设计模式,不用改变原来代码的逻辑就可以实现
   * 功能的横向扩展
   *
   * 扩展功能在scala中一般有两种方法:
   * (1)动态混入
   * (2)隐式转换的方法,在当前代码的作用域中，声明这个方法
   *
   * implicit def rddToDatasetHolder[T : Encoder](rdd: RDD[T]): DatasetHolder[T] = {
   */
  @Test
  def RDDToDFOrDS(): Unit = {
    // val rdd = sparkSession.sparkContext.makeRDD(List(1, 2, 3, 4, 5))
    //  引入session会话中的隐式


    // 将RDD转为DF
    // 使用rdd.toDF()方法
    // (1)没有参数:列名系统自定义
    // (2)有参数:参数是一个可变的字符串,作用是自定义列名

    /*val df = rdd.toDF()
    df.show()
    val df1 = rdd.toDF("age")
    df1.show()*/

    // 将RDD转为DS
    // 方法一:使用toDS方法,没有参数,列名程序自定义
    //rdd.toDS().show()

    // 方法二:使用样例类,使用样例类后,列名就是样例类的属性名,以及类型也是和样例类的属性
    // 类型一致,DataSet注重的是类型,DataFrame注重的是数据的结构,有可能出现类型转换失败
    val rdd = sparkSession.sparkContext.makeRDD(List(("zhangsan", 23), ("lisi", 29)))
      .map(line => user01(line._1, line._2))
    rdd.toDS().show()
  }

  /**
   * df.rdd转为RDD[Row]类型
   * 1,问:Row是什么?
   * 答:Row代表一行 比如[12,liabai],类似Array类型
   * 构造： Row(value,value,value)
   * 2,问:如何获取Row中的数据
   * 答:获取Row中的数据有如下方法
   * (1)Row(index)获取的都是Any类型
   * (2)row.getXxx(0) 指定获取那种类型的元素
   *
   * 总结:DF和DS转为RDD都是使用了.rdd的方法
   * 其中DF是结构化数据,转为RDD也是有结构的,就是ROW,以一行数据为单位
   * DS是有结构有类型的,转为RDD也是有结构和类型的,以对象为单位
   *
   *
   */
  @Test
  def testDFOrDSToRDD(): Unit = {
    // DF===>RDD
    /*val df = sparkSession.read.json("input")
    val rdd = df.rdd
     rdd.foreach(println)*/
    // df.toDF("name","age").show()

    // DS==>RDD
    val rdd = sparkSession.sparkContext.makeRDD(List(("zhangsan", 23), ("lisi", 29)))
      .map(line => user01(line._1, line._2))
    val ds = rdd.toDS()
    val rdd1 = ds.rdd
    rdd1.foreach(println)
  }

  /**
   * df<=>ds
   */
  @Test
  def testDFToDS(): Unit = {
    val rdd = sparkSession.sparkContext.makeRDD(List(user01("lisi", 23), user01("zhangsan", 24)))
    val df = rdd.toDF("name", "age")

    // df==>ds 使用as[样例类 类型]
    val ds = df.as[user01]
    ds.show()
    // ds==>df 使用toDF()方法
    val df1 = ds.toDF()
    df1.show()

  }

  /**
   * 创建DF的方法二
   * (1)不使用rdd.toDF
   * (2)不通过RDD创建DF的方法
   * 总结:集合中的元素必须是product的子类,其他基本类型不可以
   */
  @Test
  def testNewDF(): Unit = {
    val list = List(user01("lisi", 23), user01("zhangsan", 34))
    val rdd = sparkSession.sparkContext.makeRDD(list)
    // (1)不使用rdd.toDF
    // 直接基于RDD创建,泛型必须是Product类型
    // Int等基本类型(AnyVal) 不是product的子类
    // val df = sparkSession.createDataFrame(rdd)
    // df.show()

    //(2)不通过RDD创建DF的方法
    val df = sparkSession.createDataFrame(list)
    df.show()

  }


  /**
   * 创建DF的方法三
   * 通过DF的数据结构知道,DF的数据单元是ROW 所以创建的时候可以直接通过构建row数据集
   *
   * 问:StructType是什么
   * 答:表结构 它的构造器为;StructType(fields: Seq[StructField])
   * 问:StructField是什么
   * 答:一个字段的类型
   * 它的构造器的解析
   * case class StructField(
   * name: String,    //字段名
   * dataType: DataType,   //字段类型
   * nullable: Boolean = true,  //是否允许为NULL
   * metadata: Metadata = Metadata.empty  //其他属性)
   *
   *
   */
  @Test
  def testNewDF01(): Unit = {
    val list = List(user01("jack", 20), user01("marry", 21))
    val rdd = sparkSession.sparkContext.makeRDD(list, 2)
    val rdd1 = rdd.map(person => Row(person.name, person.age))
    val df = sparkSession.createDataFrame(rdd1, StructType(StructField("name", StringType) :: StructField("age", IntegerType) :: Nil))
    df.show()
  }
}

// 声明样例类,默认的参数是用val修饰的 有默认值
// 所有的样例类都继承了Product和Serializable
case class user01(var name: String, var age: Int)