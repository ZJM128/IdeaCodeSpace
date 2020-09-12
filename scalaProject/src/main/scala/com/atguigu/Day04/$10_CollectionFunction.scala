package com.atguigu.Day04

object $10_CollectionFunction {

  def main(args: Array[String]): Unit = {
    // 基本属性与常用的操作
    val list = List[Int](2, 4, 5, 7, 8)

    // 查看集合的长度
    println(list.size)
    println(list.length)

    // 遍历集合
    for (num<-list){
      println(num)
    }

    // 生成字符串
    println(list.mkString("@"))

    //  是否包含
    println(list.contains(3))
  }
}
