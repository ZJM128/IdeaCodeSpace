package com.atguigu.Day03

/**
 * App:代替main的方法,具有main方法的功能
 * 一般不用
 * type:给数据类型取别名
 * Enumeration:枚举类
 */
object $12_App extends App {
  println("good")
}

object TypeTest{

  type s=String
  def main(args: Array[String]): Unit = {
    val name:s="ggg"
    println(name)
    println(Color.BLUE)

  }
}

object Color extends Enumeration{
 val RED=Value(1,"red")
  val YEELLOW=Value(2,"red")
  val BLUE=Value(3,"red")
}