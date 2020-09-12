package com.atguigu.Day04
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object $11_CollectionFunction {
  def main(args: Array[String]): Unit = {
    val list = List[Int](2, 3, 5, 3, 3,1, 8, 9)
   // val listBuffer = ListBuffer[Int](2, 3, 5, 3, 1, 8, 9)

    // 删除前多少个元素
    val list1 = list.drop(2)
    println(list1)
    //listBuffer.drop(2)
   // println(listBuffer)
    // 删除后面的多少个元素
   val list2 = list.dropRight(2)
    println(list2)

    // 去重
    val list3 = list.distinct
    println(list3)
    // 获取第一个元素
    val head = list.head
    println(head)
    // 获取最后一个元素
    val last = list.last
    println(last)
    // 获取除了最后一个元素的所有的元素
    val init = list.init
    println(list)
    println(init)
    println("除开第一个")
    // 获取除了以一个元素的所有的元素
    println(list)
    val tail = list.tail
    println(tail)
    // 获取从前往后多少个元素
    val list5 = list.take(3)
    println(list5)
    // 获取从后往前的多少个元素
    val list6 = list.takeRight(3)
    println(list6)


    // 判断是否为空
    println(list.isEmpty)

    // 反转
    val reverse = list.reverse
    println(reverse)

    // 划窗 默认的步长为1 每取两个元素形成一个list集合
    val iterator = list.sliding(2)
    for (i<-iterator)println(i)

    // 不足步长的,就把剩余的单独形成一个list集合
    val sliding = list.sliding(3, 2)
    for (i<-sliding)println(i)

    // 获取子集合 from:开始角标 until:结束角标[不包含]
    // 左闭右开
    println("="*34)
    println(list)
    val list4 = list.slice(1, 3)
    println(list4)

    // 集合的操作
    //交集:取两个集合共同的部分
    val list7 = list.intersect(List(8, 9))
    println(list7)

    // 差值 A差B 保留的是A中有 B中没有的元素
    val list8 = list.diff(List(8, 9))
    println(list8)

    // 并集 不会去重 如果需要去重需要再调用distinct distinct
    val list9 = list.union(List(2, 3, 5, 7))
    println(list9)

    // 拉链 A和B进行拉链操作 A的元素在前,拉链的个数取决于最短的那个集合个数
    val tuples = list.zip(List("李四", "战三", "王五"))
    println(tuples)
    val tuples1 = List("李四", "战三", "王五").zip(list)
    println(tuples1)

    // 反拉链 把元组中第一个元素都取出来放入同一个list中
    val unzip = tuples.unzip
    println(unzip)

  }
}
