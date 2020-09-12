package com.atguigu.Day02
/**
 * 高阶函数: 参数/返回是函数的方法/函数
 * 高阶函数传参的简化:
 *   1、传入的函数可以省略参数类型
 *   2、如果函数只有一个参数，则()可以省略
 *   3、如果函数的参数在函数体中，只使用了一次，则可以通过_代替，下面两种情况除外
 *       1、如果函数参数只有一个，并且在函数体中直接返回参数本身，则参数不能用_代替
 *       2、如果函数体中有(),而且函数的参数处于函数体的()中，则不能用_代替
 *
 */
object $04_HightFunction {
  def main(args: Array[String]): Unit = {
    // 函数当参数
    var func=(a:Int,b:Int)=>a+b
    println(m1(12, 20, func))
    val func2=m2((x:Int,y:Int)=>{x+y})
    println(func2)
    println(m3((i: Int) => i * 4))
    // 函数当做返回值
    println(m4()(3, 5))
    println("@"*20)
    // 函数当参数的时候进行简化
    // 1 不简化
    println(m5(7, 3, (a: Int, b: Int) => {
      a + b
    }))
    // 1.1传入的函数可以省略参数类型,因为方法的参数中的函数的参数类型可以推断出函数的参数的类型
    println(m5(7, 3, (a, b) => {
      a + b
    }))
    // 1.2 {}可以省略,因为只有一行语句
    println(m5(7, 3, (a, b) => a + b))
    // 1.3 因为 函数的参数在函数体中只有过一次,所以可以用_代替
    println(m5(7, 3, _ + _))
    // 1.4 如果函数只有一个参数则() 可以省略
    println(m6(9, (a: Int) => a))
    println(m6(8, a => a))
    //
    // ==============================不能使用_的情况=====================================
    println("#"*3)
    // 1.1如果函数参数只有一个，并且在函数体中直接返回参数本身，则参数不能用_代替
    //println(m6(6, _)) 运行报错
    // 1.2 如果函数体中有(),而且函数的参数处于函数体的()中，则不能用_代替
    // m3((_+1)*10) 编译报错
  }




  // 把函数当做参数传入到方法中,函数名:(参数类型)=>返回值类型 可以把[(参数类型)=>返回值类型] 当做参数类型
  // 类比 参数名:参数类型
  def m1(a:Int,b:Int,func:(Int,Int)=>Int)= {
    func(a,b)
  }
  def m2(func:(Int,Int)=>Int)=func(10,20)
  def m3(func:Int=>Int)=func(50)
  // 把参数当做返回值
  def m4()={
    var func=(x:Int,y:Int)=>x+y
    // 函数就是一个对象,可以直接返回
    func
  }

  def m5(a:Int,b:Int,func:(Int,Int)=>Int)=func(a,b)

  def m6(a:Int,func:Int=>Int)=func(a)
}
