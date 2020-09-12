package com.atguigu.Day04

/**
 * 创建map 不可变map
 *  1,Map[k的类型,v的类型](k->v,k1->v1...)
 *  2,Map[K的类型,v的类型]((k,v),(k,v)...)
 */
object $07_ImmutableMap {


  def main(args: Array[String]): Unit = {

    // 1 Map[k的类型,v的类型](k->v,k1->v1....)
    val map = Map[String, Int]("zhangsan" -> 24, "lisi" -> 23, "wangwu" -> 26)
    println(map)
    // 2 Map[k的类型,v的类型]((k,v),(k,v))
    val map1 = Map[String, Int](("李白", 23), ("张飞", 35), ("甄姬", 28))
    println(map1)

    // 3获取某个Key的value值
    //println(map("zhangsan")) // 没有相应的key值会报错
    // 所以有另一种取值的方法map.get(key)
    //Option 有两个子类
    //  Some:代表有值,值封装在Some中
    //	None:代表没有值，代表提示外部进行处理
    //val value: Option[Int] = map1.get("zhangsan")
    //println(value.get)
    //getOrElse(K，默认值)
    val option = map.get("zhang")
    // 取值的时候判断一下option的值
    if (option ==null){
      println(option.get)
    }

    // 还有一种简单的取值方法 如果取不到则就把默认值赋值给他
    val age = map.getOrElse("张三","0")
    println(age)

    // 4.添加单个元素 生成新的map
    val map2 = map.+("张三" -> 23)
    println(map2)

    //  5.添加集合的数据 随机性
    val map3 = map.++(Map[String, Int]("李四" -> 23, "王五" -> 34))
    println(map3)
    val map4 = map.++:(Map[String, Int]("夏侯惇" -> 45))
    println(map4)

    // 6 删除单个元素
    println("删除")
    println(map)
    val map5 = map.-("zhangsan")
    println(map5)

    val map6 = map.--(List[String]("lisi", "wangwu"))
    println(map6)

    // 遍历Map 集合 k-v对就是一个个元组
    for (element<-map){
      println(element)
    }

    // 获取map的所有的key值
    val keys = map.keys
    for (key<-keys){
      println(key)
    }

    // 获取map的所有的value值
    val values = map.values
    for (value<-values){
      println(value)
    }
  }
}
