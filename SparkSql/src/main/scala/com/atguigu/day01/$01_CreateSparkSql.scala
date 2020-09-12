package com.atguigu.day01

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Test}

class $01_CreateSparkSql {


  //方式1
  val session: SparkSession = SparkSession.builder
    .master("local") // 提交的集群地址
    .appName("Word Count") //应用名称
    //.config("spark.some.config.option", "some-value")  //设置自定义的参数
    .getOrCreate()


  //方式2
  // 创建spark的配置信息
  private var conf = new SparkConf().setMaster("local").setAppName("mySpark")
  // 创建SparkSession对象
  private val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()

  /*
       从RDD转为DF或DS
             RDD   转为    DF/DS
             scala中   ：   ①动态混入
                            ②隐式转换（更喜欢）
                               找到隐式转换的方法，在当前代码的作用域中，声明这个方法！
  */
  import spark.implicits._
  @After
  def stop(): Unit ={
    spark.stop()
  }

  /**
   * 有关RDD,DataFrame,DataSet的操作
   */
  @Test
  def test01()={
    // 读取json文件
    val df = spark.read.json("input/person.json")
    // 默认是20

   // df.show()

    // =============sql风格====================
    // 创建临时表
    //df.createTempView("user")
    // 使用临时表
    //spark.sql("select * from user").show()
    //spark.sql("select avg(age) from user").show()

    // ==============DSL风格语句===================
    //df.select("name","age").show()
    // 可以使用rdd中的算子
    //df.filter($"age">12).show()

    // ======================RDD===>DataFrame==>DataSet=============
    val rdd = spark.sparkContext.makeRDD(List(("zhangsan", 22), ("lisi", 43)))

   //***************************RDD==>DataFrame**********************************
    //  rdd: RDD[Int] ---->rddToDatasetHolder ---->DatasetHolder.toDS
    // (1)无参数 属性名为默认值_1或_2
    //rdd.toDF().show()
    // (2)有参数,指定属性名
    //val frame = rdd.toDF("name", "age")
    //frame.show()

    // ***************************RDD==>DataSet***********************************

    // (1)无参数 属性名为默认值_1或_2
    //rdd.toDS().show()

    // (2)使用样例类的方法创建RDD ,调用RDD.toDS()
   /* val rdd1 = spark.sparkContext.makeRDD(List(("zhangsan", 22), ("lisi", 43)))
      .map(t=>user(t._1,t._2))
    rdd1.toDS().show()*/


    // *****************************DataFrame=>DataSet*************************************

    //注意 DataFrame=>DataSet的时候 需要传入as[样例类]
    val df1 = rdd.toDF("name", "age")
    val ds = df1.as[user]
    //ds.show()
  // ***********************************DataSet===>DataFrame***********************************
    val df2 = ds.toDF()
    //df2.show()

    // =======================DataFrame&DataSet==>RDD=======================================================

    // *************************DataFrame===>RDD*******************************

    // 解析:DataFrame 转为RDD 后是row类型 里面提供了getXXX的方法获取字段的值
    // 类似jdbc处理结果集,但索引从0开始
    val rdd2 = df2.rdd
    //rdd2.foreach(x=>println(x(0)))

    // ****************************DataSet===>RDD********************************************
   //
    val rdd3 = ds.rdd
    rdd3.foreach(x=>println(x.name))

  }

  /**
   * UDF的定义
   */
  @Test
  def udfTest(): Unit ={

    def addAge(age:Int): Int ={
      age+10
    }

    val rdd = spark.sparkContext.makeRDD(List(("zhangsan", 22), ("lisi", 43)))
    val df = rdd.toDF("name", "age")
    //spark.udf.register("addAge",addAge _)
    // 使用匿名函数 不能简写
    val addAge1 = spark.udf.register("addAge", (x: Int) => x + 30)
    // 创建临时表
    df.createTempView("user")
    spark.sql("select addAge(age) as newAge from user").show()
    df.select(addAge1('age))

  }

}
case class user(var name:String,var age:Int)