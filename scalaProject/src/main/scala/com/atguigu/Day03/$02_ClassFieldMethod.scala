package com.atguigu.Day03

object $02_ClassFieldMethod {

  class Person{
    /*
    定义属性
      修饰符 val/var 变量名:变量类型=值
    成员属性:
      成员变量用val定义后,不能再被重新赋值
      成员变量如果使用var定义,有默认的set方法[属性名=值],也就是可以直接赋值
    成员属性的默认值规定:
      成员变量用var修饰的时候,可以使用 _ 赋默认值,引用类型默认值 null int:0 double:0.0 Boolean:flase
     */
    val name="lisi"
    var age:Short=_
    def add(x:Int,y:Int)=x+y
  }

  def main(args: Array[String]): Unit = {
    val person=new Person
    println(person)

    // 1使用类中的成员
    println(person.age)
    println(person.name)
    println(person.add(1,2))
    //2修改成员变量的值
    //2.1 var修饰的成员变量是可以修改的
    person.age=29
    // 2.2val修饰的成员属性是不能修改的
    //person.name="张三" 报错
  }
}
