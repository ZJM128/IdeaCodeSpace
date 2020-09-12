package com.atguigu.Day01

/**
 * 块表达式
 *  1 定义: 由{}包裹的代码称之为块表达式
 *  2 块表达式有返回值,返回值就是{}中最后一行的表达式的结果值
 *
 *  for循环,while循环是没有返回值
 */
object Block_Demo {

  def main(args: Array[String]): Unit = {
    val block={
      val name="lisi"
      val age=20
      println(s"姓名${name} 年龄${age}")
      "李白"
    }
    println(block)

    val result={
      var number=30
      var number1=90
      number*number1
    }
    println(result)
  }
}
