package com.atguigu.Day03

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import scala.beans.BeanProperty

/**
 * obj.isInstanceOf 类型判断
 * obj.asInstanceOf 类型转换
 * _:类=>:自身类型提醒
 */
object $12_SelfType {

  // 自身类型：提醒子类要实现该trait的时候必须提前实现指定的trait/class
  trait ReadAndWriteObject{
    _:Serializable=>

    def read()={
      val ois=new ObjectInputStream(new FileInputStream("D:/object.txt"))
      var obj=ois.readObject()
      ois.close()
      // 返回
      obj
    }

    def write()={
      val ous=new ObjectOutputStream(new FileOutputStream("D:/object.txt"))
      ous.writeObject(this)
      ous.flush()
      ous.close()
    }
  }

  class Person(@BeanProperty val name:String,@BeanProperty val age:Int) extends ReadAndWriteObject with Serializable

  def main(args: Array[String]): Unit = {
    val person=new Person("李白",26)
    person.write()

    var person2=new Person("张三",56)
    var obj=person2.read()
    // 类型判断
    if(obj.isInstanceOf[Person]){
      //类型转换
      val person3 = obj.asInstanceOf[Person]
      println(person3.getAge)
      println(person3.getName)
    }
    /*obj match {
      case person1: Person =>
        // 类型转换
        person2 = person1
      case _ =>
    }*/
    println(person2.name)
    println(person2.age)
  }
}
