package com.atguigu.Day04

import scala.collection.LinearSeq

/**
 * 集合高阶函数
 */
object $13_CollectionFunction {
  def main(args: Array[String]): Unit = {
    //foreachTest()
    //groupByTest()
    //mapTest()
   // flattenTest()
    flatMapTest()
    //sortedTest()
    //sortByTest()
    //sortWithTest()
    //reduceTest()
    //foldTest()
    //foldRightTest()
  }

  /**
   * filter :过滤 针对的是集合的每个元素,保留的是函数返回值为true的数据
   */
  def filter(): Unit ={
    val list: List[Int] = List[Int](2, 1, 3, 4, 6, 7, 5, 8)
    val list2 = list.filter(x => x % 2 == 0)
    val list1 = list.filter(_ % 2 == 0)
    println(list1)
    println(list2)
  }
  /**
   * foreach循环遍历集合的每一个值
   *
   * @inline final override def foreach[U](f: A => U) {
   *         var these = this
   *         while (!these.isEmpty) {
   *         f(these.head)
   *         these = these.tail
   *         }
   *       }
   */
  def foreachTest(): Unit ={
    val list: List[Int] = List[Int](2, 1, 3, 4, 6, 7, 5, 8)
    //list.foreach(x=>println(x))
    //list.foreach(println(_))
    // println方法符合要求
    list.foreach(println)

  }

  /**
   * groupBy 针对的是集合中的每一个元素
   * 场景: 用于多对一的场景 类似sql中的group by 分组
   * groupBy(f:(x)=>K)中x=>k x是list集合中的元素是函数的参数,k是函数的业务逻辑,
   * 整个x=>k方法是传入x得到结果k,然后group by中的逻辑就根据k进行分组处理
   * groupBy返回的是Map类型
   */
  def groupByTest(): Unit ={
    val list = List[(String, Int, String)](
      ("zhangsan", 23, "beijing"),
      ("lisi", 23, "shenzhen"),
      ("王五", 23, "shanghai"),
      ("赵柳", 23, "shenzhen"),
      ("柳岩", 23, "beijing")
    )
    val map = list.groupBy(x => x._3)
    println(map)
  }

  /**
   * map 针对的是集合的每一个元素
   * 场景:用于一对一的场景
   *map(f: A => B) 出入一个类型返回另一个类型,把list中的每一个元素传进去返回通过业务处理的另一个类型的数据
   * map的返回类型也是集合list
   * map flatMap filter distinct sortBy
   */
  def mapTest(): Unit ={
    val list = List[String]("hello", "world", "scala", "python")
    val list1 = list.map(x => x.length)
    println(list1)
  }

  /**
   * 压平
   * 场景 一对多
   * 把集合中每一个元素传进去 返回一个或多个元素
   * 注意flatten 只对第一层list中的元素进行操作
   * 返回值也是集合list
   */
  def flattenTest(): Unit ={
    val list = List[List[Int]](
      List[Int](2, 3, 4, 5),
      List[Int](6, 7, 8, 9),
      List[Int](10, 11, 12, 13)
    )
    val flatten = list.flatten
    println(flatten)
  }
  def flattenOps(): Unit ={
    val list = List[List[List[Int]]](
      List[List[Int]](List[Int](1, 2)),
      List[List[Int]](List[Int](3, 4)),
      List[List[Int]](List[Int](5, 6))
    )
    val flatten = list.flatten
    println(flatten)
  }

  /**
   * 扁平化
   * flatmap 针对集合的每个元素=map+flatten
   * 场景:用于一对多
   * flatMap是先对集合进行map操作 再对map处理后的返回值进行flatten操作
   */
  def flatMapTest(): Unit ={

    val list = List[String]("hello word","hello python")
    //1 map + flatten
    val list1 = list.map(_.split(" "))
    // List[Array(hello,word),Array(hello,python)]
    val flatten = list1.flatten
    // list[hello,word,hello,python]
    println(flatten)

    //2,flatMap
    val list2 = list.flatMap(_.split(" "))
    println(list2)
  }

  /**
   * sorted :按照元素本身的大小进行排序,默认是升序排序
   */
  def sortedTest(): Unit ={
    val list = List[Int](1, 3, 5, 3, 2, 2, 8)
    val list1 = list.sorted
    println(list1)
  }

  /**
   * sortBy 针对集合的每个元素,返回的是按照排序字段进行排序的集合lsit[升序]
   * sortBy(f: A => B) 传入集合的每一个元素,然后返回第二个元素,然后sortBy就按照返回的
   * 值进行排序
   */
  def sortByTest(): Unit ={
    val list = List[(String, Int)](("zhansan", 25), ("lisi", 23), ("wangwu", 24))
    val list1 = list.sortBy(_._2)
    println(list1)
  }

  /**
   * sortWith 集合的每两个元素进行比较
   *
   */
  def sortWithTest(): Unit ={
    val list = List[Int](1, 3, 5, 3, 2, 2, 8)
    //降序
    val list1 = list.sortWith((x, y) => x > y)
    println(list1)
    // 升序
    val list2 = list.sortWith(_ < _)
    println(list2)
  }

  /**
   * reduce
   agg: 上一次的聚合结果
   curr: 代表本次聚合的元素
   */
   //第一次执行的时候 agg=2  curr=5   agg+curr=7
   //第二次执行的时候 agg=7  curr=1   agg+curr=8
   //第三次执行的时候 agg=8  curr=10   agg+curr=18
   //第四次执行的时候 agg=18  curr=3   agg+curr=21
   //第五次执行的时候 agg=21  curr=20  agg+curr=41
  def reduceTest(): Unit ={
     val list = List[Int](2,5,1,10,3,20)
    val i = list.reduce(_+_)
    println(i)
  }

  /**
   * reduceRight对于加法的话和reduce没有区别 但对于减法还是有区别的
   * 这个是从最后一个元素是agg
   */
  def reduceRight(): Unit ={
    val list = List[Int](2,5,1,10,3,20)
    println(list.reduceRight(_ + _))

  }

  /**
   *  fold agg的初始值为fold第一个参数列表的值
   */
  def foldTest(): Unit ={
    val list = List[Int](2,5,1,10,3,20)
    val i = list.fold(10)((agg, curr) => agg + curr)
    println(i)
  }
  //foldRigth
  def foldRightTest(): Unit ={
    val list = List[Int](2,5,1,10,3,20)
    println(list.foldRight(10)((curr, agg) => curr + agg))
  }
}
