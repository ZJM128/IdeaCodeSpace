package com.atguigu.Day05

/**
 * 偏函数:[不适用Match的模式匹配]
 *  var func:PartialFunction[IN,OUT]={
 *    case..=>..
 *  }
 */
object $09_Math_PartialFunction {
  def main(args: Array[String]): Unit = {
    var func:PartialFunction[Any,Int]={
      case x:String=>
        println("String")
        10
      case y:Int=>
       println("Int")
        20
      case _=>
        println("other")
        23
    }
    val i = func("ddd")
    println(i)

    // 偏函数的使用场景

    val school = List(
      ("宝安中学",("小学一班",("1001","zhangsan",20))),
      ("宝安中学",("小学一班",("1002","lisi",22))),
      ("宝安中学",("小学一班",("1003","wangwu",23)))
    )
    var function1:PartialFunction[(String,(String,(String,String,Int))),String]={
      case (schoolname,(classname,(sid,name,age)))=>name
    }
    for(i<-school){
      println(function1(i))
    }
    /*val list = school.map({
      case (schoolname, (classname, (sid, name, age))) => name
    })
    println(list)*/

  }
}
