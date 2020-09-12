package com.atguigu.Day02

object $05_Test {
  def main(args: Array[String]): Unit = {
    var result = getLenght(Array("hello", "word", "hadoop", "python"), array => {
      for (str <- array) yield {
        str.length
      }
    })
    println(result.toBuffer)

    println("&" * 30)
    var result1 = getOp(Array(1, 3, 6, 4, 2, 9), array => {
      for (i <- array if i % 2 == 0) yield {
        i
      }

    })
    println(result1.toBuffer)

    println("#" * 30)
    var reslut2 = sum(Array(1, 3, 6, 4, 2, 9), array => {
      var result = 0
      for (i <- array) {
        result += i
      }
      result
    })
    println(reslut2)
  }

  /* #1、定义一个高阶函数，对传入的数组中的每个元素进行操作，将其变成单词的长度
   Array("hello","word","hadoop","python")
   =>
   Array(5,4,6,6)*/
  def getLenght(array: Array[String], func: (Array[String]) => Array[Int]) = func(array)

  /* #2、定义一个高阶函数，参数两个:Array,函数，通过传入的函数对Array进行过滤，保留符合我们要求的数据
   Array(1,3,6,4,2,9)
   =>
   Array(6,4,2)*/
  def getOp(array: Array[Int], func: Array[Int] => Array[Int]) = func(array)

  /*  #3、定义一个高阶函数，参数两个:Array,函数，根据函数的规则对数组进聚合
    Array(1,3,6,4,2,9)
    =>
    25*/
  def sum(array: Array[Int], func: Array[Int] => Int) = func(array)

}
