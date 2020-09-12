package com.atguigu.Day03

/**
 * 单例对象:只有一个实例
 *  scala中单例对象就是object.如果想要创建一个单例对象,只需要创建一个object就可以
 *  object中所有方法和属性都是类似java.static修饰的,可以直接通过object名称.方法名/属性名
 *
 *  伴生对象和伴生类
 *    1,class,object必须名字一样
 *    2,class,object必须在同一个源文件[.scala]中
 *  伴生类和伴生对象可以互相访问对方private修饰的属性与方法
 *
 *  apply方法
 *  注意:只能定义在伴生对象中,apply方法是用来简化伴生类的对象创建
 *  作用:如果伴生类声明为private的时候,无法通过new的方法进行对象的创建
 *  可以使用apply方法进行对象的创建
 *  类对象的创建方式:
 *  1,new
 *  2 apply
 */
object $08_SingleObject {

  def main(args: Array[String]): Unit = {
    // object 对象是一个单例对象
    //println($08_SingleObject eq $08_SingleObject) //true

    // 伴生对象和伴生类内部相互调用
    //var person1:Person=new Person("李白",33)
    //person.show(1,1)

    // 通过apply方法进行简化类对象的创建
    val person1=Person("liability",23)
    // 直接调用apply方法创建对象
    var person=Person.apply("张三",35)
    person.show(3,4)

  }
}
object  Person {
  private def add(x: Int, y: Int) = {
    // 在伴生对象中可以访问伴生类的私有属性与方法
    var person = new Person("张三", 25)
    println("伴生类私有方法:" + person.add2(x, y))
    println("伴生类私有属性:" + person.sex)
  }

  private def sum(x: Int, y: Int) = x + y

  private val address = "深圳"
  private var age: Int = 26
  var hight:Int=167

  // 使用apply定义类对象
  def apply(name: String, age: Int): Person = new Person(name, age)
}
class  Person private(val name:String,var age:Int){
  private def add2(x:Int,y:Int)=x+y
  private val sex="男"
  def show(i:Int,y:Int)={
    //在伴生类中可以调用伴生对象的私有的属性和方法
    println("伴生对象私有属性:"+Person.address)
    println("伴生对象私有属性:"+Person.age)
    println("伴生对象私有方法:"+Person.sum(3, 5))
    println("伴生对象私有方法:"+Person.add(i, y))
  }
}