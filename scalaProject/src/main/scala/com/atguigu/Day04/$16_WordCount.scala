package com.atguigu.Day04

object $16_WordCount {
  def main(args: Array[String]): Unit = {
    val list = List(("Hello Scala Spark World", 4),
      ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))

    //1 需要把每个元组的key分割 变成=>每个小元组(Hello,4),(Scala,4)...
    val list1 = list.map(x => {
      val value = x._2
      val words = x._1.split(" ")
      for (word <- words) yield {
        (word, value)
      }
    })
    val list2 = list1.flatten

    //2 分组 以每个小元组的key进行分组
    val map = list2.groupBy(_._1)
    //3 把集合中的value中的二元元组的v进行求sum
    val result = map.map(x => {
      val key = x._1
      val sum = x._2.map(_._2).sum
     // (key, sum)
      key->sum
    })
    map.foreach(println)
  }
}
