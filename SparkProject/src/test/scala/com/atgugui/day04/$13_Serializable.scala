package com.atgugui.day04

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}


// 序列化属性
class User(age:Int=10){
  // 过滤RDD中10以下的元素返回新的RDD
  def filterRdd(rdd:RDD[Int]): RDD[Int] ={
    var myage=age
    // age称为闭包变量, 需要将User对象序列化,发送发送到Executor，才能使用User.age
    val rdd2 = rdd.filter(x => x > myage)
    rdd2
    }
}

// 序列化方法
class User01(age:Int=10){
  def function1(x:Int): Boolean ={
    x>10
  }
  // 过滤RDD中10以下的元素返回新的RDD
  def filterRdd(rdd:RDD[Int]): RDD[Int] ={
    // 声明成函数
    def function2(x:Int): Boolean ={
      x>10
    }
    //算子 fun1称为闭包变量 需要将User对象序列化，发送到Executor，才能使用User.fun1
    //val rdd2 = rdd.filter(x=>function2(x))
    // 使用匿名函数
    rdd.filter(x=>x>10)
  }
}

// 体会kryo
case class User2(age:Int=10,name:String="fhaowiehfaoiwhfoiua;whfeiawofh oi;aweh foiawhfoikjauwhfoi;auwhfeoiu;awehfaoiu;wehfaoiewu;fh")

/**
 *
 * Kryo:  高效的序列化框架！替换java.io.Serializable
 *            Serializable 在JDK1.1出现，在设计时，没有考虑大数据网络传输的问题！
 *                    Serializable在保存对象的信息时，除了属性的值，之外还会额外保存其他信息（类的继承关系等）
 *
 *              在大数据领域，只关心数据的值，不关心数据的类型，继承关系等！
 *
 *              希望在序列化时，可以仅仅保存数据的值的信息！
 *
 *              Hadoop:   Writable 替代 java.io.Serializable
 *              Spark : 不支持Writable，使用 Kryo作为序列化框架！
 *                          使用Kryo，类必须实现java.io.Serializable
 *                          Kryo基于 java.io.serilizable工作！
 *
 *                          Kryo的效率是java.io.serilizable的10倍以上
 */
class $13_Serializable {

  // 使用kryo  ===>.registerKryoClasses(Array(classOf[User2]))
  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark") .registerKryoClasses(Array(classOf[User2])))
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


  @Test
  def test01()={
    // Driver端创建
    val list = List(1, 2, 3, 4, 5)
    val rdd = sc.makeRDD(list)
    var sum:Int=10

    // 分布式运算,构成闭包,检查sun是否支持序列化,如果支持序列化
    // 此时创建sum的一个副本,将副本发送到Task执行Executor
    // 在Executor端上运算
    // 行动算子 都是分布式在每个Executor上运行的
    rdd.foreach(x => sum += x)
    println(rdd.collect().mkString(","))
    // Driver端声明的sum 没有变化
    println(sum)

  }
  /**
   * 解决办法
   *  (1)extends Serializable
   *  (2)使用样例类 case class
   */
  @Test
  def test02():Unit={
    val list = List(1, 2, 3, 4, 5)
    val rdd = sc.makeRDD(list)
    val user = new User()
    // 闭包 要求闭包中使用的外部变量必须可以序列化
    // 否则报错 Task not serializable
    rdd.map(x=>user)
  }

  /**
   * Task not serializable
   *  属性序列化
   *      属性必须依附于对象存在,对象是类的实例,类是对象的抽象
   *      解决:
   *          (1)类实现序列化
   *              ①继承序列化接口
   *              ②声明成样例类
   *          (2)可以使用方法中的局部变量接收要用的属性
   */
  @Test
  def test03():Unit={
    val list = List(1, 2, 3, 4, 5)
    val rdd = sc.makeRDD(list)
    val rdd2 = new User().filterRdd(rdd)
   // rdd2.collect()
  }

  /**
   * Task not serializable
   * 方法的序列化
   *    解决:
   *      (1)类实现序列化
   *      (2)可以在要使用的方法总,将计算的逻辑声明成函数
   *      函数声明在方法中,不是类的成员
   *      也可以使用匿名函数
   */
  @Test
  def test04():Unit={
    val list = List(1, 2, 3, 4, 5)
    val rdd = sc.makeRDD(list)
    new User01().filterRdd(rdd)
  }

  /**
   * 体会kryo
   */
    @Test
  def test05():Unit={
    // 658.0 B
    // Kryo : 302.0 B
    val list = List(User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2()
      , User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(),
      User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2(), User2())

    val rdd: RDD[User2] = sc.makeRDD(list, 2)

    // 使用带shuffle的算子测试序列化
    val rdd1: RDD[(User2, Iterable[User2])] = rdd.groupBy(x => x)

    rdd1.collect()

    Thread.sleep(100000000)
  }
}
