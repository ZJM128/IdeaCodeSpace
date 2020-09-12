package com.atguigu.Day04
/**
 * 1、创建不可变数组：不能新增和删除,可以修改
 *     1、 new Array[元素类型](长度)
 *     2、 Array[元素类型](初始元素,..)
 * 2、增删改查
 *   1、获取元素:  数组(角标)
 *   2、修改元素的值:  数组(角标) = 值
 *   3、添加元素[实际上是生成一个新的数组，原有的数组没有变化]
 *     添加单个元素:  +:与:+的区别【+:将元素添加到最前面,:+将元素添加到最后面】
 *       val 新数组 = 数组.+:(元素)
 *       val 新数组 = 数组.:+(元素)
 *    添加集合的所有元素: ++ 、++: [++将一个数组的所有元素添加到最后面，++：将一个数组的所有元素添加到最前面]
 *       val 新数组 = 数组.++(数组2)
 *       val 新数组 = 数组.++:(数组2)
 *
 * 不可变数组转可变数组: toBuffer
 */
object $01_ImmutableArray {

  def main(args: Array[String]): Unit = {
     // 1 new Array[类型](长度) 没有赋初始值,默认是0
      var array=new Array[Int](3)
      var array1=array.+:(1)
      println(array1.toBuffer)
    // 2 Array[类型](初始元素) 调用的是apply[T: ClassTag](xs: T*)方法
    var arr=Array[Int](34,5,6,7)
    println(arr.toBuffer)
    // 3 获取数据:数组对象(角标)
    println(arr(0))// 获取第一个元素
    // 4 修改数据
    arr(0)=1000
    println(arr.toBuffer)

    // 5 添加单个元素 添加元素的时候只会生成一个新的数组,原来的数据没有改变
    var arr1=arr.+:(90) // 把元素添加在原来元素的前面
    var arr2=arr.:+(80)// 吧元素添加在原来元素的后面
    println(arr1.toBuffer)
    println(arr2.toBuffer)

    // scala中==与eqauls含义一样，如果想判断两个对象是否是同一个，需要通过eq方法来判断
    println(arr1.eq(arr))

    // 6 添加集合的所有的元素
    var appendArr=Array[Int](3,4,5,6,7)
    var arr3=arr.++(appendArr) // 添加到数组的后面
    println(arr3.toBuffer)

    var arr4=arr.++:(appendArr)
    println(arr4.toBuffer) // 添加集合的所有的元素到数组的前面

  }
}
