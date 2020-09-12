package com.atguigu.Day04

import scala.io.Source

/**
 * 数字出现的次数取前三
 */
object $15_WordCount3 {
  def main(args: Array[String]): Unit = {
    // 1读取文件
    val source = Source.fromFile("d://wordcount.txt", "UTF-8")
    val list = source.getLines().toList
    // 关闭io流
    source.close()
    // 2分割,压平
    // 3分组
    // 4统计
    // 排序(降序)
    // 取值
    list.flatMap(_.split(" "))
      .groupBy(x => x)
      .map(x => {
        (x._1, x._2.size)
      })
      .toList
      .sortWith((x,y)=>x._2>y._2)
      .take(3)
      .foreach(println)




  }

}
