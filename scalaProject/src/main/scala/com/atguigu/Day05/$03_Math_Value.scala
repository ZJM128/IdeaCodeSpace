package com.atguigu.Day05

import scala.io.StdIn
import scala.util.Random

object $03_Math_Value {

  def main(args: Array[String]): Unit = {
    val list = List[Any](1, 2, "44", "good", false)
    val value = list(Random.nextInt(list.size))
    println(value)
    // 匹配类型
   /* value match {
      case key: Int => println("int")
      case key: String => println("string")
      case key: Boolean => println("Boolean")
      case _ => println("没有匹配的")
    }
*/
    //匹配的时候 如果匹配条件中是通过变量匹配
    //    1、如果变量名首字母是小写，则普通变量，后续值会传递给变量
    //    2、如果变量名首字母是大写，则匹配外部的常量，相当于就是匹配一个具体的值
    val Word = "good"
    val line = StdIn.readLine()
    line match {
      case a if Word.contains("g") => println(s"$Word")
      //case Word if Word.contains("good") => println(s"$Word")
      case _ => println("没有匹配")
    }
  }

}
