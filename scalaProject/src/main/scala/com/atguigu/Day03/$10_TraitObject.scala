package com.atguigu.Day03

/**
 * 对象混入trait:只让某些对象拥有trait对应的属性和方法
 * 如果有些对象需要用到特质中的有些功能,但是又不想全部对象都有这些功能
 * 这时候可以用混入特质
 */

object $10_TraitObject {

  class tigger {
    val name = "老虎"
  }

  trait log {
    def add(i: Int, j: Int) = i + j
  }


  class Cat  {
    val name: String = "小黄"
    val age: Int = 23

    def show(): Unit = println(s"名称:${name},年龄:${age}")
  }
  def main(args: Array[String]): Unit = {
    val cat=new Cat with log
     println(cat.add(1, 55))

   // var tigger = new tigger with log
   // println(tigger.add(1, 2))
  }

}
