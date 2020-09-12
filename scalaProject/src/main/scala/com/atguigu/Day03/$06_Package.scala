package com.atguigu.Day03
//导入包下所有类: import 包名._
import  scala.collection.mutable._
//导入包下单个类: import 包名.类名
import scala.collection.mutable.ArrayBuffer
//导入包下多个类: import 包名.{类名1,类名2,..}
import scala.collection.mutable.{ArrayBuffer,ArrayBuilder}
//导入包下类,并起别名:import 包名.{类名=>别名}
import scala.collection.mutable.{ArrayBuffer=>myBuffer}
//导入包下除开某个类的其他的所有类:import 包名.{类名=>_,_}
import java.util.{HashMap=>_,_}

/**
 * scala 中package操作
 *  1 声明包
 *    (1)第一行声明包
 *    (2)在文件中通过package 包名
 *  2导入包
 *    (1)导入包下所有的类:package 包名._
 *    import  scala.collection.mutable._
 *
 *    (2)导入包下单个类:import 包名.类名
 *    import scala.collection.mutable.ArrayBuffer
 *
 *    (3)导入包下多个类:import 包名.{类名1,类名2...}
 *    import scala.collection.mutable.{ArrayBuffer,ArrayBuilder}
 *
 *    (4)导入包下类,并起别名:import 包名.{类名=>别名}
 *    import scala.collection.mutable.{ArrayBuffer=>myBuffer}
 *
 *    (5)导入包下除开某个类的其他的所有类:import 包名.{类名=>_,_}
 *    import java.util.{HashMap=>_,_}
 *   3 注意:
 *       scala可以在任何地方导包,如果不是在文件开头导包，
 *    那么其他地方导入包只能在当前作用域以及子作用域中使用
 *   4 包对象(相当于一个工具类)
 *      包对象中定义的方法与属性可以在包中任何地方使用,作用与包下的所有的类
 */
object $06_Package {

  def main(args: Array[String]): Unit = {

    // 使用导包的使用使用自定义命名
    def myBuffer=new myBuffer
    println(myBuffer.size)

    // 在main方法中引入自定义的包
    import myPackage._
    // 使用person中的方法
    person.show
  }

}

package myPackage{
  class person
  object person{
    def show=println("自定义包")
    def main(args: Array[String]): Unit = {
      // 包对象中的方法
      packageObject()
      println("hello package")
    }
  }
}
