package com.atguigu.Day04
import scala.collection.mutable
/**
 *
 * 可变的,map集合
 * 创建map:
 *   1、Map[K的类型,V的类型]( K -> V,K->V,..)
 *   2、Map[K的类型,V的类型]( (K,V),(K,V))
 */
object $08_MutableMap {
  def main(args: Array[String]): Unit = {
    val map = mutable.Map[String, Int]("lisi" -> 23, "zhangsan" -> 25, "wangwu" -> 26)
    println(map)

    // 获取key对应的value
    val age = map.getOrElse("zhangsan", 2)
    println(age)

    // 修改value值[如果key存在,则修改,如果不存在则插入]
    map("zhangsan")=88
    map("zhaoyun")=99
    println(map)

    // 添加
    val map1 = map.+("zhaoliu" -> 34)
    println(map1)
    map.+=("luban"->11)
    println(map)
    // 添加一个map集合
    val map2 = map.++(mutable.Map[String, Int]("张飞" -> 24, "旋蒸" -> 56))
    println(map2)
    map.++=(mutable.Map[String,Int]("dubu"->44,"gej"->22))
    println(map)

    // 删除单个元素
    println(map)
    val map3 = map.-("gej")
    println(map3)
    map.-=("gej")
    println(map)

    // 删除多个元素
    val map4 = map.--(List[String]("zhaoliu", "zhaoyun"))
    println(map4)

    map.--=(List[String]("zhaoliu", "zhaoyun"))
    println(map)

  }
}
