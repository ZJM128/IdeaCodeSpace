package com.atguigu.Day03

object $01_CreateClass {

  class person
  /**
   * scala中没有public关键词,默认就是相当于public
   * class 类型名{}
   * scala中如果class中不需要定义与方法,{} 可以省略: class 类名
   * scala中创建对象的时候,如果不需要传参数,()可以省略
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val person =new person
    println(person)
    /*class person{
      val name= "张三"
      val age=20
    }
    var person=new person
    println(person.name)
*/
  }
}
