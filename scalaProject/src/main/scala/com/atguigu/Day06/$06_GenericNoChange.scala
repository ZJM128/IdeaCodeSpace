package com.atguigu.Day06

import com.atguigu.Day06.$05_GenericUpperLower.Student
import javafx.scene.Parent

/**
 *
 * 逆变: -T
 *   创建对象之后，对象的关系与泛型的关系相反
 * 协变: +T
 *   创建对象之后，对象的关系与泛型的关系保持一致
 * 非变: T
 *   创建对象之后，两个对象没有任何关系
 */
object $06_GenericNoChange {

  def main(args: Array[String]): Unit = {

    // 非变
    val value = new Base[Person]
    val value1 = new Base[Student]
    //value=value1
    // 协变
    val value2 = new Base1[Person]
    val value12 = new Base1[Student]
    //value2=value12
    // 逆变
    val value3 = new Base1[Person]
    val value13 = new Base1[Student]
    //value13=value3
  }

  class Person
  class Student extends Parent
  class Base[T]
  class Base1[+T]
  class Base2[-T]
}
