package com.atguigu.Day04

import scala.collection.mutable.ArrayBuffer

/**
 * 创建可变数组: ArrayBuffer[元素类型](初始元素,...)
 *   单个+/-与两个+/-的区别: 单个+/-针对的是单个元素，两个+/-针对是一个集合的所有元素
 *   +与-的区别： +代表添加元素,-代表删除元素
 *   带=与不带=的区别: 带=修改的是集合本身,不带=创建一个新集合，原有集合不变
 *   :在前与:在后的区别:  :在前面代表添加元素到最后面，:在后代表添加元素到最前面。不带:是将元素添加到最后面
 * 添加数据
 * 删除
 * 查看
 * 修改数据
 * 可变数组转不可变数组: toArray
 */
object $02_MutableArray {

  def main(args: Array[String]): Unit = {

    // 1 创建可变数组
    var array=ArrayBuffer[Int](1,5,6,7,8,4)
    println(array)
    // 2 修改指定角标的值
    array(0)=100
    println(array)
    //3 获取指定角标的值
    println(array(2))
    // 4,添加元素->生成新集合
      //4.1 添加单个元素->生成新集合
    var arr2=array.+:(40)// 添加在原来元素的前面
    var arr3=array.:+(50) // 添加在原来元素的后面
    println(arr2)
    println(arr3)
      // 4.2 添加集合里的元素->生成新集合
    var arr4=array.++(arr3)// 添加在原来元素的前面
    var arr5=array.++:(Array(1,4,5,6,7)) // 添加在原来元素的后面
    println(arr4)
    println(arr5)
    //5添加元素->改变集合的本身
      //5.1 添加单个元素
    println("改变集合的本身")
    println(array)
    array.+=(56) // 添加在集合的后面
    println(array)
    array.+=:(78)
    println(array)
      //5.2 添加整个集合元素
    println("添加集合中的元素")
    array.++=(Array(1,2,5,6))
    println(array)
    array.++=:(Array(66,88,99))
    println(array)

    // 6 删除元素  不改变自身
      //6.1 删除一个元素 根据元素 不是根据索引删除的,而是根据元素匹配删除
    val buffer = array.-(88)
    println(buffer)
    val array6 = array.-(0)
    println(array6)
      // 6.2 删除整个集合里的元素 删除匹配数组里的元素
    val array7 = array.--(Array(88, 99, 66,55))
    println(array7)
    // 7 删除元素 改变本身
      // 7.1 删除单个元素
    val array8 = array.-=(99)
    println(array8)
      // 7.2 删除匹配的数组的元素
    val array9 = array.--=(Array(88, 66))
    println(array9)

    // 8 创建多维数组
    val array10 = Array.ofDim[Int](3, 3)

    println(array10)
    for (arr<-array10){
      for (i<-arr)
        println(i)
    }

  }
}
