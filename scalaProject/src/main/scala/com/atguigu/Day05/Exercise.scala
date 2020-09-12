package com.atguigu.Day05

import java.text.SimpleDateFormat

import scala.io.Source

/**
 * (龙华区,613)
 * (福田区,560)
 * (宝安区,287)
 * (龙岗区,1338)
 *
 *
 */
object Exercise {

  var dataFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  def main(args: Array[String]): Unit = {

    // 读取文件
    val source = Source.fromFile("d:/wordcount.txt", "utf8")
    val list = source.getLines().toList
    source.close()
    // 分割
    val list1 = list.map(x => x.split("\t"))
    val list2 = list1.map(arr => {

      (arr(0), arr(1),arr(2),dataFormat.parse(arr(3)).getTime , dataFormat.parse(arr(4)).getTime)
    })
    // 分组
    val mapResult = list2.groupBy(x => x._1)
    // 排序
    //println(mapResult.toList)
    val list3 = mapResult.toList.map(row => {
      val key = row._1
      val value = row._2.sortBy(_._4)
      (key, value)
    })

    //list3.foreach(println)

    val list4 = list3.flatMap(line => {
      val list = line._2
      // 划窗函数
      val iterator = list.sliding(2)
      iterator
    })

   // list4.foreach(println)
   // List((A,龙华区,宝安区,1594778710000,1594779902000), (A,宝安区,龙岗区,1594780515000,1594780850000))
    val list5 = list4.map(list => {
      val dowTime = list.head._5
      val address = list.last._2
      val upTime = list.last._4
      (address, (upTime - dowTime)/1000)
    })

    list5.groupBy(_._1)
      .map(x=>{
        val address = x._1
        val list = x._2
        val sum = list.map(_._2).sum
        val size = list.size
        (address,sum/size/60)
      }).foreach(println)


  }
}
