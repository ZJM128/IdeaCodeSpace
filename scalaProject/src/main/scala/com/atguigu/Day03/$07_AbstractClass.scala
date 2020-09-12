package com.atguigu.Day03

/**
 * 抽象类:
 *  1,抽象中既可以定义具体的方法也可以定义抽象方法
 *  2,抽象类中如果定义了抽象方法,子类如果不是抽象类,那么就必须重写父类的抽象方法
 *  3,抽象类中既可以定义抽象属性[没有初始值的属性] 也可以定义具体属性
 */
object $07_AbstractClass {

  abstract class Person{
    val name="张三"
    // 抽象属性
    var age:Int
    def add(x:Int,y:Int)=x+y
    // 抽象方法
    def sun(i:Int,j:Int):Int
  }
  // 继承抽象类
  class student extends Person{
    // 重写抽象属性
    override var age: Int = 25

    // 重写抽象方法
    override def sun(i: Int, j: Int): Int = i*j
  }
  def main(args: Array[String]): Unit = {

    // 多态
    val person:Person=new student
    // 调用子类重写的age属性
    println(person.age)
    // 调用父类本身的属性
    println(person.name)
    // 调用子类的重写的sun方法
    println(person.sun(1, 45))

    // 匿名类的定义和使用
    val teacher=new Person {
      override var age: Int = 23

      override def sun(i: Int, j: Int): Int = i+j
    }
    println("teacher"*10)
    println(teacher.name)
    println(teacher.age)
    println(teacher.sun(3,5))
  }
}
