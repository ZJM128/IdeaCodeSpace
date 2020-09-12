package com.atguigu.Day04

object $18_WordCount {
  def main(args: Array[String]): Unit = {
      List(("Hello Scala Spark World", 4),
      ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))
      .flatMap(x=>{x._1.split(" ").map(key => (key,x._2))})
      .groupBy(word=>word._1)
      .map(k=>{(k._1,k._2.map(x=>x._2).sum)})
      .toList
      .sortWith(_._2>_._2)
      .take(3)
      .foreach(println)


  }
}
