package com.atguigu.Day02
/* #1、定义一个高阶函数，对传入的数组中的每个元素进行操作，将其变成单词的长度
 Array("hello","word","hadoop","python")
 =>
 Array(5,4,6,6)*/
object $06_Map {

  def main(args: Array[String]): Unit = {
    var array=Array("hello", "word", "hadoop", "python")
    // 省略参数类型
    println(mapFun(Array("hello", "word", "hadoop", "python"), (str) => {
      str.length
    }).toBuffer)
    println("#"*30)
    // 一段逻辑代码 省略{}
    println(mapFun(array, data => data.length).toBuffer)
    // 匿名函数中参数只有了一次 可以用_代替
    println("#"*30)
    println(mapFun(array, _.length).toBuffer)
  }
  def mapFun(array: Array[String],fun:String=>Int)={
    for (i<-array)yield fun(i)
  }
}
