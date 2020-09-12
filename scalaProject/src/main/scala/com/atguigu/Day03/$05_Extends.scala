package com.atguigu.Day03

import scala.beans.BeanProperty

object $05_Extends {

  class Person{
    @BeanProperty
    val name="张三"
    //@BeanProperty
    var age:Int=20
    //@BeanProperty
    val addres="shanghaoi"

    def m1(i:Int,j:Int)= i+j
  }

  class Student extends Person {
    // val 修饰的非private修饰的属性重写
    override val name="李白"
    override val addres="深圳"
    // var修饰的属性 加上override后编译不报错,但是运行报错
    //override var age=30

    // 方法重写
    override def m1(i: Int, j: Int): Int = i*j

  }
  /**
   * 1 继承
   * 那些不能被继承:
   *  1,final修饰的class不能被继承
   *  2,private修饰的属性不能被继承
   *
   *  父类var标识的属性不能被重写
   *  子类想要重写父类的方法必须通过override关键字标识
   *  子类可以重写父类val标识的非private的属性
   *
   *2 多态
   *  scala的多态不仅仅是方法的多态,属性也是多态的
   */
  def main(args: Array[String]): Unit = {

    // 继承
    val  student=new Student
    println(student.getName)
    // 取的是父类的age值
    println(student.age)
    println(student.addres)
    println("多态"*3)
    val person:Person=new Student
    println(person.addres)
    println(person.age)
    println(person.name)

    // 子类没有重写name和address的get和set的方法,但是调用name和address的
    // get和set的方法的使用值是子类的,属性也是多态的
    println("属性多态"*4)
    var s=new Student
    println(s.getName)
    println(s.addres)
    var person1:Person=s
    println(person1.getName)
    println(person1.addres)

  }

}
