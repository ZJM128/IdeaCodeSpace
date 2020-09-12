package com.atguigu.Day04

object $17_WordCount {

  def main(args: Array[String]): Unit = {
    val list = List(("Hello Scala Spark World", 4),
      ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))
    list.flatMap(x=>{x._1.split(" ").map(word=>(word,x._2))})
      .groupBy(_._1)
      .map(x=>{(x._1,x._2.map(_._2).sum)})
      .foreach(println)
    

  }
}
