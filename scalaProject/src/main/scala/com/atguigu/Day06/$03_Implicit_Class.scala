package com.atguigu.Day06

import java.io.File

import scala.io.Source

/**
 * 隐式转换类: 将一个类转成另一个类型
 * 语法:
 *   implicit class 类名(val 属性名:待转换类型) {
 *     .....
 *   }
 * 后续当对象使用了不属于自身方法的时候就可以自动的转成隐式类的类型
 * 限制:
 *   1、隐式类必须定义在object或者class中，也就是说隐式类不能作为最顶端
 */
class ImplicitClass{
  implicit class RechFile(val f:File) {
    def readLines(): Iterator[String] = {
      Source.fromFile(f).getLines()
    }

    def add() = {
      3 + 9
    }
  }
}
object $03_Implicit_Class {
  /*implicit class RechFile(val f:File){
    def readLines(): Iterator[String] ={
      Source.fromFile(f).getLines()
    }
    def add()={3+9}
  }*/
  def main(args: Array[String]): Unit = {
    val implicitClass = new ImplicitClass()
    import implicitClass.RechFile
    // 当声明类型变量类型的时候:如果声明的类型和隐式类中的待转换类型一致,则该变量就就有隐式类的全部的属性和方法
    val file = new File("d:/file.txt")
    println(file.add())
  }
}
