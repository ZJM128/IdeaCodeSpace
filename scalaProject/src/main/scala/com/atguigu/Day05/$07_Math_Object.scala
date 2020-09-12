package com.atguigu.Day05

import scala.beans.BeanProperty

/**
 * 样例类(声明样列类,底层自动生成unapply ,apply方法)
 *  语法:case class 类名([val/var] 属性名:属性类型)
 *  样例对象(一般用于枚举)
 *  语法:case object object名称
 */
case class Person(@BeanProperty name:String, @BeanProperty val age:Int)
trait SEX

case object Man extends SEX
case object Woman extends SEX

class Student(var name:String,val age:Int)
object Student{
  def apply(name: String, age: Int): Student = new Student(name, age)
  def unapply(arg: Student): Option[(String, Int)] = {
    if (arg==null)
      None
      else
      Some((arg.name,arg.age))
  }
  def math01(sex:SEX): Unit ={
    println("===========")
  }
}

object $07_Math_Object {
  def main(args: Array[String]): Unit = {
    List[String]()
    // 样例类的使用
    val person = Person("zhangsan", 26)

    println(person.age)
    println(person.getName)
    // 样列对象
    Student.math01(Man)

    // 用样例类=>模式匹配
    person match {
      case Person(name,age)=>println(s"age:$age")
    }

    //不用样例类需要声明unapply方法
    val student = Student("lisi", 23)
    student match {
      case Student(name,age)=>println(s"name:$name")
    }
  }
}
