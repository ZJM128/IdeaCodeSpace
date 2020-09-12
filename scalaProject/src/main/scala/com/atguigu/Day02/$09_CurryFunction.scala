package com.atguigu.Day02

/**
 * 柯里化:有多个参数列表的方法称为柯里化
 * 作用:1简化嵌套函数开发
 *      2,将复杂的参数逻辑简单化,可以支持更多的语法
 */
object $09_CurryFunction {
  def main(args: Array[String]): Unit = {

    //println(fun(1, 2)(2)(6))
    //  方法里返回方法,层层调用
    var fun2=fun1(1,2)
    var fun3=fun2(3)
    println(fun3(4, 5))
    println("简化写法")
    var result=fun1(1,2)(3)(4,5)
    println(result)


  }

  def fun(a:Int,b:Int)(c:Int)(d:Int) = a+b+c+d

  def fun1(a:Int,b:Int)={
    def fun2(c:Int)={
      def fun3(d:Int,f:Int):Int={a+b+c+d+f}
      fun3 _
    }
    fun2 _
  }
  def func(a:Int)(b:Int)(func:(Int,Int)=>Int)=func(a,b)
}
