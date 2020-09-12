package com.atguigu.Day04

/**
 * 元组:由()包括起来的称之为元组
 * 定义:
 *    1:(不同类型的值)   比如val t=("张三","男",23)
 *    2:二元元组:(k->v) 也就是map中的key->value
 *    元组一旦定义,不但长度不可变,元素也是不可以改变的
 *
 *    获取元组的值:变量._角标[角标从1开始]
 */
object $09_Tuple {
  def main(args: Array[String]): Unit = {
    // 元组的定义
    var tuple1=("李白",23,"男")

    println(tuple1._1)
    val tuple = ("李四", 23)
    println(tuple._2)
    // kv结构就是值二元元组
    var tuple2="白居易"->87
    println(tuple2._2)

    println(tuple1)

  }
  def tuple1(): Unit ={

  }
}
