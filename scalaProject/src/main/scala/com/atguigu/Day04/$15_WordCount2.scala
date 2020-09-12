package com.atguigu.Day04

object $15_WordCount2 {
  def main(args: Array[String]): Unit = {
    val list = List(("Hello Scala Spark World", 4), ("Hello Scala Spark", 3), ("Hello Scala", 2), ("Hello", 1))
     list.flatMap(x => {
      val words = x._1.split(" ")
      words.map(word => (word, x._2))
    }).groupBy(xml=>xml._1)
       .map(xml=>{
         var word=xml._1
         val sum = xml._2.map(y => y._2).sum
         (word,sum)
       }).foreach(println)


  }

}
