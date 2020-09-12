package com.atguigu.Day02

/**
 * 控制对象
 * Breaks:捕捉异常
 * breakable是一个函数
 * 参数列表中如果有多行逻辑,可以采用大括号代替
 * scala支持将代码逻辑作为参数传递给函数使用
 * 如果函数参数想要传递代码逻辑,那么类型声明的方式应该为:
 * 参数名: =>返回值类型(unit)
 * 因为参数类型中没有声明小括号,所以调用是也不能加小括号
 */
object $12_Breaks {

  def main(args: Array[String]): Unit = {
    //val number=10
    //val number1=100
    //func(println(number+number1))

    func2("hello")(
      {
        println("scala")
      }, {
        "good"
      }
    )
  }
  def func(f: =>Unit)={
    f
  }

  def func2(flag:String)(f: =>Unit,f1: =>String)={
    println(flag)
    f
    f1
  }
}
