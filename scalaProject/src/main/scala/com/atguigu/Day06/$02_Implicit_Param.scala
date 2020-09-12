package com.atguigu.Day06
/**
 * 隐式转换参数:
 *   语法: implicit val 变量:类型 = 值
 * 隐式转换参数要想使用必须在定义方法的时候表明哪个参数是要使用隐式转换参数
 * 定义方法的时候，隐式转换的参数单独放在一个参数列表中
 */

object $02_Implicit_Param {

  implicit var age:Int=90
  def main(args: Array[String]): Unit = {
    getAge("zhijunm")
  }

  /**
   * 隐式转换参数,必须在方法声明的时候手动指定
   * @param name
   * @param age
   */
  def getAge(name:String)(implicit  age:Int)={
    println(name+"="+age)
  }
}
