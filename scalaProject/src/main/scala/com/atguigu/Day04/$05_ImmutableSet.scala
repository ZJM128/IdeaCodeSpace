package com.atguigu.Day04

/**
 * set 不可变的特性:无序 不重复
 */
object $05_ImmutableSet {
  def main(args: Array[String]): Unit = {
      // 创建set对象
      val set = Set[Int](1, 3, 7, 6, 5)
    // 1set没有取元素的方法
    //println(set(0)) 表示set集合是否包含

    // 2添加 单个元素
    val set1 = set.+(66)
    println(set1)
    // 添加集合里的数据
    val set2 = set.++(Array(2, 5, 7, 8))
    println(set2)

    val set3 = set.++:(Array(2, 5, 7, 0))
    println(set3)

    // 删除元素
    val set4 = set.-(1)
    println(set4)
    // 删除集合的数据
    val set5 = set.--(set)
    println(set5)
  }
}
