package com.atguigu.Day06

/**
 * 上下界
 *    上界 "<:" 泛型必须是指定的类型或子类
 *    下界 :> 泛型必须是执行类的父类或者其本身
 *
 */
object $05_GenericUpperLower {
  class  Person{
    def show(): Unit = println("Person")
  }
  class Student extends Person{
    def getName(): Unit ={
      println("name")
    }
  }
  class base extends Person{
    override def show(): Unit =println("Base")
  }

  def main(args: Array[String]): Unit = {

    // 上限 本身或者子类
    m1(new Student)
    m1(new Person)
    // 下限  本身或者父类
    m2(new base)
    m2(new Person)
  }
  def m1[T<:Person](t:T): Unit ={
    t.show()
  }
  def m2[T>:base](t:T)={
    println(t)
  }
}
