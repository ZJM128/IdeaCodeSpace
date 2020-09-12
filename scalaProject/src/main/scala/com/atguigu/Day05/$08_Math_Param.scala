package com.atguigu.Day05

/**
 * 元组的匹配
 */
object $08_Math_Param {
  def main(args: Array[String]): Unit = {
    val tuple = math01(2, 3)
    // 模式匹配的方式
    tuple match {
      case (x,y,z)=>println(z)
    }
    // 简写的  直接匹配返回值元组的每一个元素
    val (x,y,z) = math01(1, 5)
    println(x)
    println(y)
    println(z)
    println("list")
    // 类似的其他的数据类型也可以
    val List(_,_,g,_*) = List(2, 3, 4, 6, 7)
    println(g)

    println("array")
    val Array(a,b,c) = Array("张三", "李白", "杜甫")
    println(a,b,c)

    println("map")
    val map = Map[String, Int]("zhangsan" -> 23, ("libai", 24))
    for ((k,v)<-map)println(k,v)
    for (i<-map)println(i)
  }

  def math01(x:Int,y:Int) ={
    println(s"${x} ${y}")
    ("*"*x,"+"*y,s"${x}+${y}=${x+y}")
  }
}
