package com.atguigu.Day02

object $14_MRFTest {
  def main(args: Array[String]): Unit = {
   var array=Array("hello", "good", "zhijunming", "taiger")
    println(map(array, (word: String) => {
      word.length
    }).toBuffer)
    println(map(array, _.length).toBuffer)

    var array1=Array(1,2,3,4,5,6,7,8)
    println(filter(array1, (number: Int) => {
      number % 2 == 0
    }).toBuffer)
    println(filter(array1, _ % 2 == 0).toBuffer)

    var array2=Array(1,2,3,4,5,6)
    println(reduce(array2, _ + _).toByte)

  }
  def map(array: Array[String],func:String=>Int)={
    for (word<-array)yield
      func(word)
  }

  def filter(array: Array[Int],func:Int=>Boolean)={
    for (number<-array if func(number))yield{
      number
    }
  }

  def  reduce(array: Array[Int],func:(Int,Int)=>Int)={

    var result=array(0)
    for (index<-1 to array.length-1){
      result= func(result,array(index))
    }
    result
  }
}
