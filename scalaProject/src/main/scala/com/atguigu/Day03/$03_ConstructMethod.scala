package com.atguigu.Day03

object $03_ConstructMethod {

  /**
   * scalade 构造器分为两种:主构造器,辅助构造器
   *        主构造器:定义在类名后面通过([private val/var] 变量名:变量类型 [=默认值])表示
   *                val:定义的参数不能被重新赋值
   *                var:定义的参数可以被重新赋值
   *                val/var:定义的参数直接可以当做class的成员属性来进行使用,可以在外部通过对像
   *                获取或者赋值
   *                没有val/var:定义的参数只能在class的内部使用
   *         辅助构造器:
   *                定义位置:辅助构造器定义在class内部
   *                语法:def this(变量名:变量类型,...){
   *                    主构造器/其他辅助构造器 // 辅助构造器第一行必须调用主构造器其他的辅助构造器
   *
   *                }
   * @param name
   * @param age
   * @param address
   */
  class Person(val name:String="李四",var age:Int,address:String,val className:String){

   /* def getAddress()={
      address
    }*/
    private var withdraw=90
  }

  class Student(val name:String="李四",var age:Int,var address:String){// 主构造器
    // 辅助构造器的参数声明=>变量名:变量类型 不能使用val/var 因为主构造器中已经声明过了
    // 辅助构造器要经过初始化主构造器才能初始化
    def this(name:String)={ // 辅助构造器
      // 使用变量名进行赋值
      this(name=name,age=23,address="深圳")// 显示调用主构造器,->可以使用默认的值
    }
    // 辅助构造器可以重载
    def this(age:Int)={
      this(age=age,address="上海")
    }


  }

  def main(args: Array[String]): Unit = {
    val person=new Person("张三",23,"深圳","0523")
    // 1 使用val/var修饰的参数直接变成class的成员变量,有点像全局变量,
    println(person.name)
    println(person.age)
    println(person.className)
    //var修饰的可以修改值 val修饰的不能改变值
    person.age=90
    //person.name="333"
    // 2 不使用val/var修改的参数,只能在class的内部使用,class的外部不能使用,有点像局部变量
    //println(person.getAddress())

   var student =new Student(23)
    println("#"*20)
    println(student.age)
    println(student.address)
    println(student.name)


  }
}
