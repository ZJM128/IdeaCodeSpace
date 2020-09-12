package com.atguigu.Day01

/**
 * while和do-while的用法和java的一样
 * while 和 do while的区别
 * while是先判断再循环
 * do-while是先循环再判断
 */
object WhileAndDoWhile_Dome {
  def main(args: Array[String]): Unit = {
    val number=11
    while (number<=10){
      println("hello")
    }

    do {
      println(number)
    }while(number<=10)
  }
}
