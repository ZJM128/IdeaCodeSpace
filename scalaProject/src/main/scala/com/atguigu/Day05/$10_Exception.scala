package com.atguigu.Day05

import java.sql.{Connection, DriverManager, PreparedStatement}

import scala.util.Try



/**
 * scala中方法不可以抛出异常,没有throws关键字
 * try{}catch{}finally{}只在一种的场景用到
 * 使用外部链接的是有会用到
 */
object $10_Exception {

  def main(args: Array[String]): Unit = {

   // println(method01(3, 0))
    //method02()

    /*val value = method03(16,8)
    value match {
      case Left(x)=>println(x)
      case Right((e,y))=>println(y,e.getMessage)
      case _=>println("没有结果")
    }

    if(value.isLeft)
      println(value.left.getOrElse("没有值"))*/

    // 获取某个年龄的时候有些输出缺失了,导致获取的时候可能出现异常
    var list=List[String](
      "1,zhangsan,20,shenzhen",
      "lisi,30,shanghai",
      "2,wangwu,30,shanghai"
    )
    // 手动过滤掉
/*
    val list1 = list.map(line => {
      val arr = line.split(",")
      // 第一种做法
      try {
        arr(2).toInt
      } catch {
        case exception: Exception => 0
      }
    })
    println(list1.filter(_ != 0))*/

    // 使用Try类 然后通过filter过滤出成功的结果
   /* println(list.map(Line => {
      val value = Line.split(",")
      var age: Try[Int] = Try(value(2).toInt)
      age
    }).filter(_.isSuccess).map(_.get))*/

   /* println(list.map(line => {
      val value = line.split(",")
      Try(value(0).toInt)
    }).filter(_.isSuccess).map(_.get))*/

    //println(Try(1 / 0).getOrElse("除数为零"))
    val triedInt = Try(1 / 0)
    println(triedInt.getOrElse("除数为零"))
  }

  // try{}catch{使用模式匹配}finally{}
  def method01(a: Int, b: Int): Int = {
    try {
      if (b == 5) throw new Exception("bbb")
      a / b
    } catch {
      case e: Exception =>
        println(e.getMessage)
        0
    } finally {
      println("无论怎么样都执行到的")
    }
  }

  /**
   * scala 连接mysql try{}catch{case e:Exception=>}finally{资源的关闭}
   */
  def method02(): Unit = {
    var connection:Connection=null
    var statement:PreparedStatement=null
    try {
      //1 加载数据库驱动
      classOf[com.mysql.jdbc.Driver]
      // 获取连接
      connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","root")
      var sql=
        """
          |select *
          |from score
          |""".stripMargin
      // 创建 statement
      statement=connection.prepareStatement(sql)
      val result = statement.executeQuery()
      while (result.next()){
        print(result.getString(1)+" ")
        print(result.getString(2)+" ")
        print(result.getString(3))
        println()
      }
    } catch {
      case e: Exception => println(e.getMessage)
    }
    finally {
      // 资源关闭
      statement.close()
      connection.close()
    }
  }

  /**
   * 将正常的结果封装在Left中
   * 将有异常的结果封装Right中
   * 使用的的是有可以用模式匹配的方法得到结果
   * val value = method03(16,8)
   * value match {
   * case Left(x)=>println(x)
   * case Right((e,y))=>println(y,e.getMessage)
   * case _=>println("没有结果")
   * }
   *也可以直接获取正常的运行结果,过滤异常的结果
   * if(value.isLeft)
   * println(value.left.getOrElse("没有值"))
   *
   * @param a
   * @param y
   * @return
   */
  def method03(a:Int,y:Int): Either[Int, (Exception, Int)] ={
    try{
      Left(a/y)
    }catch {
      case exception: Exception=>
        Right(exception,y)
    }
  }
}
