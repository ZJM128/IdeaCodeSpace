package com.atguigu.Day06

import java.io.File

import scala.io.{BufferedSource, Source}



/**
 * 隐式方法: 悄悄的将一个类型转成另一个类型
 *  语法:implicit def 方法名(参数名:待转换):目标类型={}
 *  隐式转换方法必须定义在object中
 *  隐式转换方法的调用的时机:
 *    1,当使用类型与目标类型不匹配的时候会使用到隐式转换方法
 *       在使用隐式转换的时候，会自动从当前作用域中查找是否有符合的隐式转换方法，如果有，直接使用。
 *       如果隐式转换方法定义在其他的object中，那么要想使用隐式转换方法，必须导入才行
 *    2、当对象使用不属于自己的方法/属性的时候会使用到隐式转换
 */
object AA{
  implicit def doubleToIntFunction(x:Double):Int=x.toInt
  implicit def doubleToIntFunction2(x:Double):Int=2*x.toInt
}
/*object AA{
  implicit def doubleToIntFunction(x:Double):Int=x.toInt
}*/
object $01_Implicit_Method {
  //implicit def doubleToInt(x: Double):Int=x.toInt

  def main(args: Array[String]): Unit = {
    //1、当使用类型与目标类型不匹配的时候会使用到隐式转换方法
    //在使用隐式转换的时候，会自动从当前作用域中查找是否有符合的隐式转换方法，如果有，直接使用。
    //   如果隐式转换方法定义在其他的object中，那么要想使用隐式转换方法，必须导入才行
    //var a:Int=10.0
    //如果隐式转换方法定义在其他的object中，那么要想使用隐式转换方法，必须导入才行
    //import AA._
    //var number:Int=19.0
    //如果定义隐式转换的object中有多个隐式转换方法都符合要求，那么必须明确指定[明确导入]使用哪一个隐式转换方法
    import AA.doubleToIntFunction
    var number:Int=19.0

    //当对象使用不属于自己的方法的时候会使用到隐式转换
    val file = new File("d:/hello.txt")
    file.getLines()
  }
  implicit def fileToSource(f:File):BufferedSource={
   Source.fromFile(f)
  }
}
