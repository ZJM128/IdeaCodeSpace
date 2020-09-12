package com.atguigu.Day02

/**
 * 普通递归
 * 递归:自己调用自己
 * 1:在方法体中要有退出条件
 * 2:方法必须定义返回值类型
 *
 * 尾递归
 * 递归的方法不依赖于其他外部的变量
 * 编译器碰见尾递归操作的时候自动化优化为while循环
 */
object $11_Recursive {
  def main(args: Array[String]): Unit = {
    //println(func(5))
    //println(func2(5,1))

    var function0=(num:Int,num2:Int)=>{num+num2}
    println(function0(2, 4))
  }

  /**
   * 普通递归
   * @param number
   * @return
   */
  def func(number: Int):Int={
    if (number == 1) {
          1
    }else {
       number +func(number - 1)
    }
  }

  /**
   * 尾递归
   * 尾递归其实就是从尾部开始进行数据的处理
   * 比如
   * 4  5+1
   * 3  4+5+1
   * 2 3+4+5+1
   * 1 2+3+4+5+1
   */
  def func2(num:Int,result:Int):Int={
    if (num==1){
      result
    }else{
      func2(num-1,result+num)
    }
  }

  def fun3(num:Int,startNun:Int):Int={
    if (num==1){
      startNun
    }else{
      fun3(num-1,num+startNun)
    }
  }
}
