package com.atguigu.Day01

object FunctionTest {
  def main(args: Array[String]): Unit = {
    //def fun(password:String,name:String="zhangsan",age:Int) = password+name+age

   // println(fun("1233", age = 21))
    //()=>println("ss")

    /*def fun1():String={
      "zhangsan"
    }
    var a=fun1();
    var b=fun1 _
    println(a)
    println(b)
    println(b())*/

/*
    def fun2(f:(Int,Int)=>Any): Unit ={
      println(f(10,10))
    }

    fun2(_*_)*/

   /* def fun(i:Int): Int ={
      i*30
    }

    def fun01() ={
      fun _
    }

    println(fun01()(10))*/

    /*def fun02(i:Int): Int ={
      i*60
    }
    def fun()={
      fun02 _
    }
    println(fun()(12))*/

    /*def fun04(f:Int=>Int): Int ={
      f(20)
    }
    println(fun04((number:Int)=>{number*2}))

    println(fun04((number)=>{number*2}))

    println(fun04((number)=>number*2))

    println(fun04(Number=>Number*2))

    println(fun04(_*2))*/
/*
    def fun()={
      val number=100
      def fun1() ={
        number*2
      }
      fun1 _
    }
    println(fun()()) ;*/


    /*def fun(i:Int)(j:Int)={
      i*j
    }
    println(fun(20)(20))*/
/*
    def fun(op: =>Unit)={
      op
    }
    fun{
      println("ddddd")
    }
    */
    /*var fum=(number:Int)=>{
      println(number)
    }
    fum(90)*/
/*
    def fum(age:Int)={
      println(age)
    }
    fum(30)*/


    // 函数的声明方式
    /*var fun=(i:Int,j:Int)=>i+j
    println(fun(3, 4))

    def fun1(i:Int,j:Int)=i+j
    println(fun1(4,3))

    var func1=(str:String,str1:String)=>str+str1
    println(func1("1", "3"))*/

    // 1,把函数当做一个对象传入参数
    var sum01=(i:Int,j:Int)=>i+j
    println(sum(2, 4, sum01))
    println("="*20)
    // 2,把用匿名函数对象进行传入参数
    println(sum(2, 3, (i: Int, j: Int) => {
      j + i
    }))
    // 3函数参数中的参数的类型可以通过推断得到,所以可以省略
    println(sum(2, 3, (j, i) => {
      i + j
    }))
    // 4,函数参数中的参数在函数体中只用了一次,所以可以用_代替
    println(sum(2, 4, _ + _))

  }

  def sum(i:Int,j:Int,fun:(Int,Int)=>Int)=fun(i, j)

}
