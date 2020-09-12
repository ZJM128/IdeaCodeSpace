package com.atguigu.Day02
/* #2、定义一个高阶函数，参数两个:Array,函数，通过传入的函数对Array进行过滤，保留符合我们要求的数据
  Array(1,3,6,4,2,9)
  =>
  Array(6,4,2)*/
object $06_Filter {
  def main(args: Array[String]): Unit = {
    var array=Array(1,3,6,4,2,9)
    println(filterMethod(array, (Number: Int) => {
      Number % 2 == 0
    }).toBuffer)

    println(filterMethod(array, Number => Number % 2 == 0).toBuffer)

    println(filterMethod(array, _ % 2 == 0).toBuffer)
  }
  def filterMethod(array: Array[Int],func:Int=>Boolean): Array[Int] ={
    for (number<-array if func(number) )yield
         number
  }
}
