package com.atgugui.day03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

import scala.reflect.ClassTag

/**
 *1,函数签名
 * def sortBy[K](
 * f: (T) => K,
 * ascending: Boolean = true,
 * numPartitions: Int = this.partitions.length)
 * (implicit ord: Ordering[K], ctag: ClassTag[K]): RDD[T]
 * 2,函数说明
 * 该操作用于排序数据,在排序之前,可以将数据通过f函数进行处理,之后按照f函数处理
 * 的结果进行排序,默认为正序排序,排序后产生的rdd的分区数与原来的RDD的分区数一致
 * 3,作用的结果;
 *    正序排序,分区数和原来的分区分区数一致
 * 4,sortBy： 有shuffle
 * 5如何分区： 不是Hash分区，使用RangePartitioner分区！
 *
 * def sortByKey(ascending: Boolean = true, numPartitions: Int = self.partitions.length)
 *  : RDD[(K, V)] = self.withScope
 * {
 *  val part = new RangePartitioner(numPartitions, self, ascending)
 *  new ShuffledRDD[K, V, V](self, part)
 *  .setKeyOrdering(if (ascending) ordering else ordering.reverse)
 * }
 */
case class Person(var name:String,var age:Int)
case class Person1(var name:String,var age:Int) extends Ordered[Person1]{
  override def compare(that: Person1): Int = {
    var result = this.name.compareTo(that.name)
    if (result==0)
      result=this.age.compareTo(that.age)
    result
  }
}
class $08_SortBy {

  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark"))
  @Before
  def start(): Unit ={
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path,true)
  }
  @After
  def stop(): Unit ={
    sc.stop()
  }

  /**
   * sortBy按照f的返回值进行排序
   * 默认是升序
   */

  @Test
def sortByTest(): Unit ={
  val rdd = sc.makeRDD(List(1, 2, 3, 54, 5, 6),2)
  val rdd2 = rdd.sortBy(number => number)
  rdd2.saveAsTextFile("output")
}

  /**
   * ascending = false 改变排序的规则
   * numPartitions=4 修改分区的个数
   * 如何分区:
   *    使用RangePartitioner分区
   */
  @Test
  def sortByTest01(): Unit ={
    val rdd = sc.makeRDD(List(1, 2, 3, 54, 5, 6),2)
    val rdd2 = rdd.sortBy(number => number,ascending = false,4)
    rdd2.saveAsTextFile("output")
  }

  /**
   * 如果排序的key是一个对象,该如何进行排序
   * //要求先按照名称降序排序，之后名称相同的按照年龄降序排序    K=>Tuple2
   * // val result: RDD[Person] = rdd.sortBy(person => (person.name, person.age), false,1)
   * //要求先按照名称降序降序，之后名称相同的按照年龄升序排序    K=>Tuple2
   *
   * 如何实现排序：  无比较，不排序！
   * 比较： compareTo()
   * Ordered:  需要让自定义的类，混入Ordered，在类中实现compareTo()
   * 将Ordered类型的类，称为可以排序的类！
   * Ordering：  比较器。可以使用排序器对针对类型进行排序
   * 使用Ordering, 自定义的类不需要混入Ordered特质。
   * 在Ordering提供针对排序类的compareTo()
   * 更灵活
   */
  @Test
  def sortByTest03(): Unit ={
    val list = List(Person("marry", 20), Person("tom", 30),Person("jack", 20), Person("jack", 21) )

    val rdd: RDD[Person] = sc.makeRDD(list, 2)

    //要求先按照名称降序排序，之后名称相同的按照年龄降序排序    K=>Tuple2
    // val result: RDD[Person] = rdd.sortBy(person => (person.name, person.age), false,1)

    //要求先按照名称降序降序，之后名称相同的按照年龄升序排序    K=>Tuple2
    val result: RDD[Person] = rdd.sortBy(person => (person.name, person.age),numPartitions = 1)
    (Ordering.Tuple2(Ordering.String, Ordering.Int), ClassTag(classOf[(String, Int)]))

    result.saveAsTextFile("output")
  }

  /**
   * 混入Ordered 实现compare方法
   */
  @Test
  def sortByTest04(): Unit ={
    val list = List(Person1("a", 20), Person1("b", 30),Person1("c", 20), Person1("a", 21) )

    val rdd: RDD[Person1] = sc.makeRDD(list, 2)

    //要求先按照名称降序排序，之后名称相同的按照年龄降序排序    K=>Tuple2
    // val result: RDD[Person] = rdd.sortBy(person => (person.name, person.age), false,1)

    //要求先按照名称降序降序，之后名称相同的按照年龄升序排序    K=>Tuple2
    val result: RDD[Person1] = rdd.sortBy(person => (person.name, person.age),numPartitions = 1)

    result.saveAsTextFile("output")
  }

  /**
   * 元组 中的元素是可以直接比较大小的,每个元素的排序规则一样
   */
  @Test
  def test01():Unit={
    val list = List(("a", 2), ("a", 3), ("b",5 ), ("c", 4), ("b", 4))
    val rdd = sc.makeRDD(list)
    val rdd2 = rdd.sortBy(tuple2 => tuple2,ascending = false)
    rdd2.saveAsTextFile("output")
  }


}
