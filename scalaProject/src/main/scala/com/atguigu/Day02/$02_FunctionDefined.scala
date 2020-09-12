package com.atguigu.Day02

object $02_FunctionDefined {

  /**
   * 函数定义:
   *     val 函数名 = (参数名:参数类型,...) => {函数体}
   *  在函数中不能使用return
   *  函数必须先定义在调用
   *
   *   函数就是对象
   *  方法与函数的区别:
   *     1、方法是随着类的加载而加载，函数只有定义在类中的时候才会随着类的加载而加载,如果定义在方法里面，只有调用方法的时候才会加载
   *     2、方法放在方法区中,函数放在堆中
   *
   *   方法转成函数:  方法名 _
   *   函数调用的时候必须带上()
   *
   *   函数的简化原则: 如果函数体中只有一行语句，则{}可以省略
   */
  def main(args: Array[String]): Unit = {
    // 1 函数的调用,必须加上(),不加()只是函数对象不具有运行的功能
    println(add(1,23))

    // 函数必须先定义再调用
    // add1() 报错,add1函数还没有声明
    var add1=(i:Int,j:Int)=>{i+j}

    // 随着类的加载而加载
    printStr("zhangsan")
    // 随着main方法的加载而加载
    var printStr2=(name:String)=>{print(name)}
    printStr2("礼拜")

    println("方法可以转为函数")
    def add2(x:Int,y:Int):Int={
      x+y
    }
    // 这个是调用函数,并把结果赋值给addFun
    var addFun=add2(2,3)
    println(addFun)
    // 把一个方法转为一个函数对象,需要用方法名 _进行转换
    var fun=add2 _;
    println(fun(2,4))

    // 函数的简化
    printStr3()

  }
  //1 函数的定义 val 函数名=(参数名:参数类型,..)=>{函数体}
  val add=(i:Int,j:Int)=>{i+j}

  // 2 函数声明在类中,会随着类的加载加载,如果声明在方法中会随着方法的调用而加载
  val printStr=(name:String)=>println(name)
  // 3 函数的简化原则:如果函数体只有一行语句,则可以省略{}
  var printStr3=()=>println("scala")




}
