package com.atguigu.Day03

import scala.beans.BeanProperty

object $04_PackageClass {

  /**
   *封装:属性私有化 提过公有的get/set方法
   * scala提供了@BeanProperty,会自动生成属性的get/set方法
   */

  class Person{
    // 为了兼容java,使用@BeanProperty提供get和set方法
    @BeanProperty // 不用手动声明get和set方法
    val name="张三"
    @BeanProperty
    var age:Int=_
  }
  def main(args: Array[String]): Unit = {
    var person =new Person
    println(person.getAge)
    println(person.getName)

  }
}
