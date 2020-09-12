package com.atguigu.Day03

/**
 * 1,Trait的基本语法:trait 特质名 {...}
 * 2,trait的说明:scala中trait既可以定义抽象方法也可以定义具体的方法
 * 既可以定义抽象属性也可以定义具体的属性
 * 3,子类继承特质的规则
 * (1)如果子类不需要继承class,第一个trait使用extends继承,后继的trait使用with关键字继承
 * (2)如果子类需要继承class,trait就只能通过with关键字继承
 * 4,scala也是单继承多现实
 *
 */
// 抽象类
abstract class Animal {
  // 抽象属性
  val name: String
  //具体属性
  var age: Int = 23

  // 抽象方法
  def add(i: Int, j: Int): Int

  def show()

  // 具体方法
  def sum(x: Int, y: Int) = x + y
}

// 脚的特质
trait leg {
  // 抽象属性
  val number: Int
  // 具体的属性
  val name = "脚"

  // 抽象方法
  def legNum(num: Int)

  // 具体方法
  def show() = println(s"脚的个数${number}")
}

// 皮肤特殊
trait color {
  val color: String

  def getColor() = println(s"颜色是$color")
}

class Cat extends Animal with leg with color {
  // 重写的方法
  override def add(i: Int, j: Int): Int = i * j

  // 重写的属性
  override val number: Int = 4

  override def legNum(num: Int): Unit = println(num)

  override val color: String = "黑白"
}

object $09_CreateRTrait {

  def main(args: Array[String]): Unit = {
    var cat = new Cat
    println(cat.add(2, 3))
    cat.getColor()
    cat.show()
  }
}
