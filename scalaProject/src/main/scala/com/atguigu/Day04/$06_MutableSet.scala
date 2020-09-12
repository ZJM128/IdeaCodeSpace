package com.atguigu.Day04
import scala.collection.mutable
/**
 * 可变set集合
 */
object $06_MutableSet {

  def main(args: Array[String]): Unit = {
    // 创建
    val set = mutable.Set[Int](3, 5, 3, 2, 1)

    // 添加元素 添加一个元素 生成新的集合
    val set1 = set.+(7)
    println(set1)
    //添加元素 本身改变了
    set.+=(66)
    println(set)
    // 添加一个集合的元素 生成新的集合
    val set2 = set.++(Array(2, 5, 3))
    println(set2)
    // 添加一个集合的元素 本身改变
    set.++=(Array(2,5,6,8))
    println(set)

    // 删除单个 生成新的集合
    val set3 = set.-(5)
    println(set3)
    // 删除单个元素,改变本身
    set.-=(5)
    println(set)

    // 删除集合中的数据 生成新集合
    println(set.--(Array(1, 3, 4)))
    // 删除集合的数 改变集合的元素
    set.--=(Array(2,4,56))
    println(set)

  }
}
