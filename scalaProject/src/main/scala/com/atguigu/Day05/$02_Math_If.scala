package com.atguigu.Day05

import scala.io.StdIn

/**
 * 守卫
 * 模式匹配语法
 *    变量 match{
 *      case 值 if 布尔表达式=>匹配的逻辑
 *      case 值 if 布尔表达式=>匹配的逻辑
 *      case 值 if 布尔表达式=>匹配的逻辑
 *      case =>不匹配的逻辑
 *    }
 *
 *    模式匹配都是有返回值的
 */
object $02_Math_If {
  def main(args: Array[String]): Unit = {
    val word = StdIn.readLine()
    val result = word match {
      case x if x.contains("hello") => {println(s"${x}")
        20}
      case y if y.contains("hadoop") =>{println(s"$y")
        10}
      case x if x.contains("good") => {println(s"${x}")
        24}
      case _ => println("啥都不匹配")
    }
    println(result)
  }

}
