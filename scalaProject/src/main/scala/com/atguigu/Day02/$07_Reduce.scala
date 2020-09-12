package com.atguigu.Day02

/*  #3、定义一个高阶函数，参数两个:Array,函数，根据函数的规则对数组进聚合
  Array(1,3,6,4,2,9)
  =>
  25*/
object $07_Reduce {
  def main(args: Array[String]): Unit = {
    var array=Array(1,3,6,4,2,9)
    println(reduceTest(array, _ + _))
  }
  def reduceTest(array: Array[Int],func:(Int,Int)=>Int)={
    var sum=array(0)
    for (number<-array){
      sum=func(sum,number)
    }
    sum
  }
}
