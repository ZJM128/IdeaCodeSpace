package com.atguigu.Day04
import scala.collection.mutable

/**
 * 可变队列
 */
object $19_MutableQueue {

  def main(args: Array[String]): Unit = {
    val queue = mutable.Queue[Int](1, 2, 4, 56)

    //  获取数据
    val value = queue.dequeue()
    println(value)
    println(queue.dequeue())
    // 添加数据
    println(queue.+:(23))
    println(queue.:+(44))

    queue.+=(33)
    println(queue)

    queue.+=:(44)
    println(queue)

    // 添加多个数据
    val queue1 = queue.++(List(3, 4, 6))
    println(queue1)
    queue.++=(List(3,6,7))
    println(queue)
  }
}
