package com.atguigu.Day04

/**
 * 创建不可变list:List[数据类型](值)
 */
object $03_ImmutableList {
  def main(args: Array[String]): Unit = {
    // 1创建
    val list = List[Int](1, 2, 3, 4)
    // 2获取元素 list(角标)
    val number = list.head
    println(number)
    val number2 = list(3)
    println(number2)

    // 3 修改集合 生成新的集合,原来的集合不改变
    val list2 = list.updated(0, 199)
    println(list2)
    //4 添加元素 生成新的集合,原来的集合不改变
      // 4.1 添加单个元素
    val list1 = list.+:(78)
    println(list1)
    val list3 = list.:+(999)
    println(list3)
    val list4= list.::(888)// 加在集合的最前面
    println(list4)
      //4.2 添加一个集合的所有的元素
      val list5 = list.++(List(90, 34, 56))
    println(list5)
    val list6 = list.++:(List(889, 23))
    println(list6)

    // 加在集合的最前面
    val list7 = list.:::(List(89, 90, 88))
    println(list7)

  }
}
