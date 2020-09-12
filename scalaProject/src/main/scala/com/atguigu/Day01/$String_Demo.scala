package com.atguigu.Day01

/**
 * 1,通过 ""声明
 * 2,通过插值s"dd${name}"
 * 3,通过""" """三引号
 * 4,format "%s".format("scala") 插值字符串
 */
object $String_Demo {
  def main(args: Array[String]): Unit = {
    //test03
    test04()
  }
  def test01():Unit={
    //1, 通过 ""声明
    val name="张三"
    println(name)
    // 2,通过插值s"dd${name}"
    val name1=s"hello ${name}"
    println(name1)

    // 3,通过""" """三引号
    var name2:String=
      s"""
         |select *
         |from person
         |where a=${name}
         |""".stripMargin
    println(name2)
    //4,format "%s".format("scala") 插值字符串
    val name4="hello %s word".format("scala")
    println(name4)
  }

  /**
   *1 Sting字符串,scala本身没有String,用的是java中的string类型,java中的string具有
   * 不可改变性,修改string的值会产生一个新的字符串,本身并没有改变
   */
  def test02():Unit={
    val name="hello scala"
    val name_sub=name.substring(0,2)
    println(name)
    println(name_sub)
  }

  /**
   * 字符串的连接
   * 传值字符串
   * 插值字符符
   * 多行字符传
   */
  def test03():Unit={
    //字符串的连接
    val name="zhangsan"
    println(name+"sss")
    println("----------------传值字符串----------")
    //传值字符串
    printf("are you ok %s",name)
    "I ok, and you %s ".format(name)
    println("----------------插值字符符----------")
    //插值字符符
    println(s"what you name ${name.toUpperCase}")
    println("----------------多行字符串----------")
   // 多行字符串
    val sql=
      s"""
        |select *
        |from person
        |where name${name}
        |""".stripMargin
    println(sql)
  }

  def test04(): Unit ={
    val str=new String("11")
    val str1=new String("11")
    println(str==str1)
    println(str.equals(str1))
    println(str.eq(str1))
  }

}
