package com.atguigu.Day03

/**
 * 1 继承多个有相同方法的trait时会出现报错的问题
 * (1)scala 中可以实现多个trait,如果这么多个trait(没有共同的父类)都有相同的同名方法,
 * 此时子类创建的对象在执行的时候就会报错
 * (2)解决方法:直接重写同名方法,如果在方法中调用父类的同名方法,实际上调
 * 用的是实现顺序中最后一个trait的同名方法
 * (3)同名方法的执行顺序[继承的多个trait有一个共同的父trait/class]:
 * ①如果同名方法中都调用了父类的同名方法:按照继承顺序从右往左执行
 * ②如果只有子类调用了父类的同名方法:按照继承顺序从左往右执行
 * (4)继承trait时实例化的顺序:按照继承/实现的顺序从左往右开始示例化(父类时第一个开始实例化的)
 *
 **
 */
trait Log{
  println("Log=======================")
  def log(msg:String)=println(s"log $msg")
}
trait ErrorLog extends Log{
  println("ErrorLog========================")

  override def log(msg:String)={
    println(s"error++ $msg")
    super.log(msg)
  }
}
trait WarnLog extends Log{
  println("WarnLog========================")
  override def log(msg:String)={
    println(s"warn $msg")
    super.log(msg)}
}

trait ErrorLog1 {
  println("ErrorLog========================")
   def log(msg:String)=println(s"error $msg")
}
trait WarnLog1{
  println("WarnLog========================")
  def log(msg:String)=println(s"warn $msg")
}
class AppLog extends WarnLog1 with ErrorLog1{
  println("AppLog=======================")

  // 子类重写两个父类的相同方法后,子类对象调用时会调用自己的方法
  // 如果在方法中调用父类的方法,则从左往右进行方法的覆盖
  override def log(msg: String): Unit = super.log(msg)

}

class AppLog1 extends WarnLog with ErrorLog{
  println("AppLog=======================")
  override def log(msg: String): Unit ={
    println(s"appLog1=====================$msg")
    super.log(msg)
  }

}
object $11_TraitMethod {

  def main(args: Array[String]): Unit = {

    //val appLog=new AppLog
   // (1)scala 中可以实现多个trait,如果这么多个trait(没有共同的父类)都有相同的同名方法,
    // 此时子类创建的对象在执行的时候就会报错
   // appLog.log("good") //没有重写相同方法时,运行报错

    // (4)继承trait时实例化的顺序:按照继承/实现的顺序从左往右开始示例化(父类时第一个开始实例化的)
    val appLog1=new AppLog1
    appLog1.log("hello")//error++ hello  warn hello
  }

}