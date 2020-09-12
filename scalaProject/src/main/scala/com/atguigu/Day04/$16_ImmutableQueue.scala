package com.atguigu.Day04
import scala.collection.immutable.Queue
object $16_ImmutableQueue {

  def main(args: Array[String]): Unit = {
    val queue = Queue[Int](1, 4, 6, 8)

    // 添加元素
    val queue1 = queue.+:(4)
    println(queue1)
    val queue3 = queue.:+(77)
    println(queue3)
    // 添加多个元素
    val queue2 = queue.++(List(2, 3, 6, 78))
    println(queue2)
    val queue4 = queue.++:(List(8, 5, 4, 3))
    println(queue4)

    //  可以添加单个数据,也可以添加多个数据
    val value = queue.enqueue(1)
    val value2 = queue.enqueue(33)
    println(value)
    println(value2)
    println(queue)
    println(queue.enqueue(List(2, 3, 4, 5)))
    // 获取数据
    println(queue.dequeue._1)
  }
}
