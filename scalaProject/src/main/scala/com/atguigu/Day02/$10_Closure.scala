package com.atguigu.Day02

import java.util

import scala.collection.mutable

/**
 * 闭包:闭包函数,在函数体中使用了不属于函数本身的变量
 * 闭包:函数在使用外部变量,如果外部变量失效时,会将这个变量包含到当前的函数内部
 * 形成闭合的使用效果,改变变量的生命周期 将这种操作称之为闭包
 *
 * 匿名函数肯定为闭包
 * 将函数赋值给变量使用也是闭包
 * 嵌套的内部函数在外面使用(使用内部函数)也是闭包
 * private static final int func1$1(int b, int a$1)
 * {
 * return a$1 + b;
 * }
 * 2.12版的scala闭包的实现是利用传参的方式把被弹栈的变量传给下一个方法
 */
object $10_Closure {
  def main(args: Array[String]): Unit = {
   // println(func(3)(4))
   // println(func3(3)(4))
    var data=new util.HashMap[String,String]()
    data.put("scala","baidu.com")
    data.put("good","wangyi.com")
    data.put("hello","guigu.com")
    var array=Array("scala","good","hello")
    def map(array: Array[String],func:String=>String) ={
      for (word<-array) yield
        func(word)
    }
    println(map(array, data.get(_)).toBuffer)

  }

  def func(a: Int) = {
    def func1(b: Int) = a + b
    func1 _
  }

  var func3 = (a: Int) => {
    var func4 = (b: Int) => a + b
    func4
  }
}
