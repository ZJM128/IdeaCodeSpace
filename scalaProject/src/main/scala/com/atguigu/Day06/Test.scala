package com.atguigu.Day06

import scala.io.Source

/**
 * 1、求出哪些省份没有农产品市场
 * 2、获取农产品种类最多的三个省份
 * 3、获取每个省份农产品最多的三个农贸市场
 */
object Test {
  def main(args: Array[String]): Unit = {

    // 读取数据
    val source = Source.fromFile("D:/exect/product.txt")
    lazy val listProduct = source.getLines().toList
    val source1 = Source.fromFile("D:/exect/allprovince.txt")
    lazy val listProvince = source1.getLines().toList
    //getNoCity(listProduct,listProvince)
    //getThreeMax(listProduct)
    getThreeMaxByProvince(listProduct)
  }
  // 1、求出哪些省份没有农产品市场
  def getNoCity(listProduct: List[String], listProvince: List[String]): Unit = {

    // 分割 过滤 列裁剪
    val listDistinct = listProduct.filter(_.split("\t").size == 6)
      .map(line => {
        val list = line.split("\t")
        list(list.length - 2)
      })
      // 去重
      .distinct

    // 去差值
    listProvince.diff(listDistinct)
      // 显示
      .foreach(println)
    //3、从产品数据中取出省份字段

  }
  // 2、获取农产品种类最多的三个省份 (山东,134),(江苏,167),(北京,169)
  def getThreeMax(listProduct: List[String]): Unit = {
    // 过滤 分割 去重
    listProduct.filter(_.split("\t").length == 6)
      .map(line => {
        val arr = line.split("\t")
        var product = arr.head
        var province = arr(arr.length - 2)
        (product, province)
      })
      .distinct
      // 按省份进行分组
      .groupBy({
        case (product, province) => province
      })
      // 统计
      .map(x => {
        var province = x._1
        var total = x._2.size
        (province, total)
      })
      // 转换
      .toList
      // 排序
      .sortBy(_._2)
      // 去前三
      .takeRight(3)
      // 展示
      .foreach(println)
  }

  // 3、获取每个省份农产品最多的三个农贸市场
  def getThreeMaxByProvince(listProduct: List[String]): Unit = {
    // 1 分割 ,过滤,列裁剪,去重
    listProduct.filter(_.split("\t").length == 6)
      .map(line => {
        val arr = line.split("\t")
        (arr(0), arr(arr.length - 2), arr(arr.length - 3))
      })
      .distinct
      // 分组 按照省份和市场进行分组
      .groupBy({
        case (product, province, city) => (province, city)
      })
      // 统计
      .map(data => {
        var (province, city) = data._1
        var total = data._2.size
        (province, city, total)
      })
      // 再一省份进行分组
      .groupBy({
        case (province, city, total) => province
      })
      // 取每个省份的前三位
      .map(data => {
        data._2.toList.sortBy({ case (province, city, total) => total }).takeRight(3)
      })
      // 遍历
      .foreach(println)

  }
}
