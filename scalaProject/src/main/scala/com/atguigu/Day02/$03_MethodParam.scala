package com.atguigu.Day02

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import javafx.scene.chart.PieChart.Data

/**
 * java中方法的参数:
 *   1、类型 参数名
 *   2、类型... 参数名
 * scala中方法的参数
 *   1、参数名 参数类型
 *   2、默认值: 参数名 参数类型=值
 *   3、可变参数: 参数名 参数类型*
 *     可变参数不可以与默认值一起使用
 *     可变参数必须放在最后面
 *
 * 方法在调用的时候，可以通过带名参数给指定的参数进行赋值
 * 将数组/集合的所有元素放入可变参数中:   数组:_*
 */
object $03_MethodParam {
  def main(args: Array[String]): Unit = {
    // 有返回值和有参数
    println(add(1, 3))
    // ==============================默认参数=======================
    // 有默认值的参数,不传值则用默认值
    println(add1())
    // 第一个参数就是赋值给第一参数
    println(add1(1))
    // 显示声明给那个参数赋值,需要把参数名写上
    println(add1(y=2))
    //=====================可变参数================================
    // 可变参数 可以是0个或多个
      println(add2())
    // 也可以多个
    println(add2(1, 2, 3))

    // 传入任意类型的参数
    printAny(1, "aa", Array[String]("a", "d"))
    // 传入数组,:_* 代表把数组中每一个元素都传到可变变量中
    printArray(Array[Int](1,2,3):_*)

    readPath(getPath(4):_*)

  }
  // 有参数,有返回值->参数名:参数类型
  def add(x:Int,y:Int)=x+y
  // 有默认值的参数->参数名:参数类型=默认值
  def add1(x:Int=0,y:Int=0)=x+y

  //可变参数
  def add2(x:Int*)=x.size
  // 可变参数的后面不能有参数
  def add3(x:Int,y:Int*)=y.sum+x
  // 报错,默认参数和可变参数不能一起使用,因为调用方法的时候参数传值会有歧义
  //def add4(x:Int=0,y:Int*)=y.sum+x
  // 定义可变参数可以传入任意类型的参数 需要用到any
  def printAny(x:Any*)=println(x.size)
  // java中可变参数可以传入数组,scala中也可以 但是需要特定的格式 数组:_*
  def printArray(x:Int*)=x.size

  def getPath(day:Int)={
    val prefix="/user/hive/warehouse/user_info/"
    val format=new SimpleDateFormat("yyyyMMdd")
    for (i<-1 to day)yield{
      val date= new Date()
      val calendar=Calendar.getInstance()
      calendar.setTime(date)
      calendar.add(Calendar.DAY_OF_YEAR,-i)
      val str=format.format(calendar.getTime)
      s"${prefix}${str}"
    }
  }
  def readPath(path:String*)={
    for(str<-path)println(str)
  }
}
