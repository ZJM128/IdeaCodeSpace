package com.atguigu.Day01

/**
 * object : scala语言中没有静态的语法,采用object方式模仿java中的静态语法
 * 通过类名访问方法。
 * def : define缩写，表示声明方法的关键字
 *
 * scala => args: Array[String] ： 参数声明 =>  参数名:参数类型
 * => 名称 ： 类型
 * 农业文明 & 工业文明
 * java => String[] args => 强类型语言 => 类型 名称
 *
 * Array : scala中的数组， 类似于java中的中括号
 * [String] : 表示泛型
 *
 * scala => main ：Unit => 方法名：类型
 * java  => void main => 类型 方法名
 *
 * Unit(类型) <==> void（关键字）
 *
 * = ： 赋值，方法也是对象，可以赋值
 *
 * Scala语言是基于java语言开发的。所以可以直接嵌入java代码执行
 *
 * Scala语言中默认会导入一些对象，那么这些对象的方法可以直接使用
 * 类似于java中import java.lang
 */
object HelloWord {
  def main(args: Array[String]): Unit = {
    /*var userName :String ="甄姬";
    println(userName)
    println("===================")
    var age =90;
    println(age)
    var width:Int =90;
    println(width)*/
   // test()
    var name:String="李白"
    println(name)
  }

  def test():Unit={
    // 变量值是字符串scala可以通过类型推断,得到数值是什么类型,可以省略
    // 显式声明类型
    var name="李白";
    var age =120;
    println(name);
    println(age);
  }
  def test01():Unit={
    // 必须声明和初始化一起,而且必须显式初始化
     //var name;
     val age =120;
  }
  def test02():Unit={
    // 可变变量,类型不可变
    var userName :String="龙皇";
    userName="哪吒"
   // userName=true;编译报错
  }

  def test03():Unit={
    // 不可变变量 类型java中的final修饰的变量
    val userName :String="白居易"
    //userName="杜甫"; 编译报错
  }
}
