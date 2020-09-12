package com.atguigu.Day02

/**
 * 匿名函数: 没有函数名的函数
 * 匿名函数不能直接调用,只能作为值进行传递
 */
object AnonymityFunction {
  def main(args: Array[String]): Unit = {
    var array=Array("hello","words","scala")
    println(filterWord(array, _.contains("s")))
  }
  def filterWord(array: Array[String],func:String=>Boolean)={
    var wordLine=""
    for (word<-array if (func(word))) {
      wordLine=","+word+wordLine
    }
    wordLine.substring(1)
  }
}
