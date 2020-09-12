package com.atguigu.Day03
import com.alibaba.fastjson.JSON
/**
 *  获取类的class： classOf[类名]
 *  获取对象的class: 对象.getClass
 */
import scala.beans.BeanProperty
object $13_ClassOf {
  class Person(@BeanProperty val name:String,@BeanProperty val age:Int)
  case class Student(name:String,age:Int)
  def main(args: Array[String]): Unit = {
    val json=
      """
        |{"name":"lisi","age":12}
        |""".stripMargin

    //java:
    //  1、根据类获取class。类名.class
    //  2、如果根据对象获取class。 对象.getClass
    var person=JSON.parseObject(json,classOf[Person])
    println(person.age)
    println(person.name)

    var student=JSON.parseObject(json,classOf[Student])
    println(student.age)
    println(student.name)
  }
}
