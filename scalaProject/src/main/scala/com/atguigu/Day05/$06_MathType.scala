package com.atguigu.Day05


object $06_MathType {
  def main(args: Array[String]): Unit = {

    val tuple = ("zhangsan", 20, "shenzhen")
    tuple match {
     // case (name:String,_:Int,_:String)=>println(s"name:$name")
      case (name,_,_)=>
        println(s"name:$name")
    }

    var school=List(
      ("宝安中学",("小学一班",("1001","zhangsan",20))),
      ("宝安中学",("小学一班",("1002","lisi",22))),
      ("宝安中学",("小学一班",("1003","wangwu",23)))
    )

    // 取出学生姓名 这种方法辨识清楚,需要写注释才知道表达的是什么
    println(school.map(_._2._2._2))
    //用匹配的方式进行取值
    val list = school.map(line => {
      line match {
        case (schoolname, (classname, (sid, name, age))) => name
      }
    })
    println(list)

    // 简化版
    val list1 = school.map {
        case (schoolname, (classname, (sid, name, age))) => name
      }
   println(list1)

  }
}
