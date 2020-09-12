package com.atguigu.Day04

import scala.collection.mutable.ListBuffer

/**
 * 创建可变的list集合:ListBuffer[类型](值....)
 */
object $04_MutableList {

  def main(args: Array[String]): Unit = {
    // 1创建可变的list集合
    val listBuffer = ListBuffer(1, 2, 3, 4, 50)

    // 2,获取数据 list(角标)
    println(listBuffer.head)
    println(listBuffer(3))

    // 3 修改数据
    // 3.1 修改集合的本身
    listBuffer.update(0, 100)
    println(listBuffer)
    // 3.2 生成新的集合,不修改本身
    val listBuffer1 = listBuffer.updated(1, 555)
    println(listBuffer1)

    // 4,添加元素
    //4.1 生成新的集合 添加单个元素
    val listBuffer2 = listBuffer.+:(99)
    println(listBuffer2)
    val listBuffer3 = listBuffer.:+(889)
    println(listBuffer3)
    // 4.2 修改集合本身, 添加单个元素
    listBuffer.+=(909)
    println(listBuffer)
    val listBuffer4 = listBuffer.+=:(8899)
    println(listBuffer4)
    //4.3  生成新集合 添加整个集合数据
    val listBuffer5 = listBuffer.++(List(90, 33, 444))
    println(listBuffer5)
    val listBuffer6 = listBuffer.++:(List(3, 5, 6))
    println(listBuffer6)
    // 4.4 改变集合本身,添加整个集合的数据
    listBuffer.++=(List(909,888))
    println(listBuffer)
    val listBuffer7 = listBuffer.++=:(List(55, 8888))
    println(listBuffer7)
    // 5 删除数据
    // 5.1 生成新集合 删除一个元素
    val listBuffer8 = listBuffer.-(8888)
    println(listBuffer8)
    // 5.2 删除本身 删除一个元素
    listBuffer.-=(888)
    println(listBuffer)

    //5.3 删除一个集合 生成一个新的集合
    val listBuffer9 = listBuffer.--(List(55, 8888))
    println(listBuffer9)
    // 5.4 删除一个集合 修改本身
    listBuffer.--=(List(55,8888))
    println(listBuffer)


  }
}
