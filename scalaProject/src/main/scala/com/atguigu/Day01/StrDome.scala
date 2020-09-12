package com.atguigu.Day01

object StrDome {
  def main(args: Array[String]): Unit = {
      test05()
  }

  /**
   * 字符串的不可变性,就是java的string类型,scala本身没有string类型
   */
  def test01():Unit={
    val userName="李白";
    var name=userName.substring(0,1)
    name="李太白";
    println(userName)
    println(name)
  }

  /**
   * 字符的串联
   */
  def test02():Unit={
    var userName="李白"
    println(userName+" is good boy")
  }

  /**
   * 传值字符串
   */
  def test03():Unit={
    var userName="李白"
    printf("name=%s",userName)
  }

  /**
   * 插值字符串
   */
  def test04():Unit={
    var userName="liability"
    println(s"name=${userName}")
  }

  /**
   * 多行字符串
   */
  def test05():Unit={
    // 多行格式化字符串
    // 在封装JSON或sql是比较常用
    // | 默认顶格符
    var name="李白"
    println(
      s"""
         |Hello
         |${name}
         |""".stripMargin)
  }
}
