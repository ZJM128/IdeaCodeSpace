package com.atguigu.Day04

/**
 * 并列集合
 */
object $21_PairList {
  def main(args: Array[String]): Unit = {
    val list = List[Int](2, 4, 5, 6, 7)
    list.foreach(x=>{
      println(Thread.currentThread().getName)
      println(x)
    })
    println("&"*23)

    // 多线程打印
   list.par.foreach(x=>{
     println(Thread.currentThread().getName)
     println(x)
   })
  }
}
