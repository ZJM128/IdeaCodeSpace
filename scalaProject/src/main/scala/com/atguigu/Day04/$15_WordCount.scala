package com.atguigu.Day04

import scala.io.Source

object $15_WordCount {

  def main(args: Array[String]): Unit = {
    // 1 读取文件
    val list = Source.fromFile("d:/wordcount.txt", "utf-8").getLines().toList
   /* hello python hello hadoop
      hello scala hello java
      java and scala and hadoop
      flume hadoop kafka hbase
      spark scala hadoop kafka*/

    println(list)
    // 2 切割 压平
    val list1 = list.flatMap(_.split(" "))
    //List(hello python hello hadoop, hello scala hello java, java and scala and hadoop,
    // flume hadoop kafka hbase, spark scala hadoop kafka)
    println(list1)
    // 分组
    val map = list1.groupBy(x => x)
    //  Map(java -> List(java, java), kafka -> List(kafka, kafka), hadoop -> List(hadoop, hadoop, hadoop, hadoop),
    // spark -> List(spark), scala -> List(scala, scala, scala), python -> List(python), flume -> List(flume),
    // hello -> List(hello, hello, hello, hello), hbase -> List(hbase), and -> List(and, and))

    println(map)
    // 统计
    val result = map.map(x => {
      var word=x._1
      (word,x._2.size)
    })
    println(result)

  }
}
