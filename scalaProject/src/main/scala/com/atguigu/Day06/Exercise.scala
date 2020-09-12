package com.atguigu.Day06

import java.text.SimpleDateFormat

import scala.io.Source
import scala.reflect.runtime.universe.Try
import scala.util.control.Breaks.{break, breakable}

object Exercise {

  def main(args: Array[String]): Unit = {
    getNoCity()
    //getMaxProvince()
   // getMaxOneProvince()
  }
  /**
   * 求每个用户每个小时最多的登录次数
   */
  def getOneHourBynumber(): Unit ={
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    // 读取文件
    val source = Source.fromFile("d:/word.txt")
    val listLine = source.getLines().toList
    source.close()
    val list = listLine.map(list=>{
      val arr = list.split(",")
      (arr(0),dateFormat.parse(arr(1)).getTime)
    })
    // 排序
    val list1 = list.sortBy(_._2)
    // 分组
    // b -> List((b,1594450272000), (b,1594447500000), (b,1594451720000), (b,1594452305000),
    //           (b,1594457100000), (b,1594457736000), (b,1594457996000), (b,1594460112000), (b,1594461539000)),
    val map = list.groupBy(_._1)

    // 对每个集合进行处理
    val result = map.map(map => {
      val name = map._1
      val list = map._2
      val listTime = list.map(_._2)
      // 定义最大值
      var maxNum = 0
      // 从尾部开始减去第一个 取小于1个小时的
      // 从尾部开始减去第二个 取小于1个小时的 ....
      for (i <- listTime.indices;j <- (listTime.size - 1) to i by -1) {
        var time = listTime(j) - listTime(i)
        breakable {
          if ((time / 1000 / 60 / 60) < 1) {
            // 因为从零开始的 所以加1
            var temp = (j - i)+1
            if (temp > maxNum) {
              maxNum = temp
            }
            break()
          }
        }
      }
      (name, maxNum)
    })
    // 打印结果 Map(b -> 4, a -> 6)
    println(result)
  }
  /**
   * 求出哪些省份没有农产品市场
   */
  def getNoCity(): Unit ={
    val source = Source.fromFile("D:/exect/product.txt")
    val line = source.getLines().toList
    source.close()
    val source1 = Source.fromFile("D:/exect/allprovince.txt")
    val listProvince=source1.getLines().toList
    source1.close()
    //println(listProvince)
    // 分割
    val list1 = line.map(x => {
      val listWord = x.split("\t")
      (listWord(0),listWord(listWord.size-2),listWord(listWord.size-3))
    }).filter(x=>x._2 !="")
    val list2 = list1.map(_._2).distinct
    //println(list2)
    val listDiff = listProvince.diff(list2)
    println(listDiff)

  }
  /**
   * 2、获取农产品种类最多的三个省份
   */
  def getMaxProvince(): Unit ={
    val source = Source.fromFile("D:/exect/product.txt")
    val line = source.getLines().toList
    source.close()
    //println(listProvince)
    // 分割
    val list1 = line.map(x => {
      val listWord = x.split("\t")
      (listWord(0),listWord(listWord.size-2))
    }).filter(x=>x._2 !="").distinct
    // 获取每个省份农产品最多的三个农贸市场
    val list = list1.groupBy(_._2).map(x=>{
      var name=x._1
      val list = x._2.map(_._1)
      (name,list.size)
    }).toList
      .sortBy(_._2)
      .takeRight(3)
      .foreach(println)


  }
  //3、获取每个省份农产品最多的三个农贸市场
  def getMaxOneProvince(): Unit ={
    val source = Source.fromFile("D:/exect/product.txt")
    val line = source.getLines().toList
    source.close()
    // 分割
    val list1 = line.map(x => {
      val listWord = x.split("\t")
      (listWord(0),listWord(listWord.size-2),listWord(listWord.size-3))
    }).filter(x=>x._2 !="").distinct

    // 根据省份和市场进行分类
    val resutMap = list1.groupBy(x => {
      (x._2, x._3)
    })

    resutMap.toList
      .map(x => {
        val provinceName = x._1._1
        val cityName = x._1._2
        val count = x._2.size
        (provinceName, cityName, count)
      }).groupBy(_._1)
      .map(y => {
        val list = y._2.sortBy(_._3).takeRight(3)
        list
      }).foreach(println)

  }
}
