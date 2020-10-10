package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}

import scala.collection.mutable
import org.junit.{After, Before, Test}
/**
 *
 *    SparkCore:    RDD（使用最广泛，最基础）
 *
 *              针对Job中共享变量：
 *                     广播变量：    用来在Job中将共享的只读变量，通过广播的方式发送所有的Executor，每个Executor上执行的
 *                                task共享变量！
 *                                Executor可以从广播网络中的任意节点（而不是局限于Driver）来获取广播变量！*
 *                                在Spark内部，会将不同阶段用到的共用的数据，以广播变量的形式进行发送！*
 *                                 广播变量 缓存在机器上，而不是 随着每一个Task进行传输！
 *                                 场景： 每一个task，在不同的阶段需要使用共同的数据（大数据）！*
 *                                 使用：   var broadcastVar=SparkContext.broadcast(v)  //创建广播变量
 *                                          broadcastVar.value  //访问广播变量*
 *                                          .unpersist()   // 释放executor上的广播变量，释放后，如果后续使用可以重新广播
 *                                          .destroy()    //   释放executor上的广播变量，不可以再获取*
 *                                  一旦创建了broadcastVar，就必须使用broadcastVar而不是v！
 *
 *                     累加器: (需要行动算子触发)
 *                              作用： 在Job中适用于仅仅是追加操作的场景，类似MR中的计数器Counter 或 Sum求和操作！
 * *                                    spark默认只提供了针对数值类型累加的累加器，可以扩展！
 * *                                    SparkContext.longAccumulator() or SparkContext.doubleAccumulator()*
 *                               方法：  add() : 累加
 *                                      value() : 获取累加的结果
 *                                      必须实现的方法： reset() : 将累加器重置归0
 *                                                      merge() : 将其他相同类型的累加器进行合并！*
 *                               在executor上不能读累加器的值，只能在Driver端获取累加器的值；
 *                               程序员可以通过extends AccumulatorV2实现自己的累加器！*
 *                               使用：  SparkContext.register(自定义累加器)
 *                               调用的流程: 对象是每个task
 *                                        copy()--->reset()-->isZero-->序列化
 */
class $05_BroadCastAndACC {


  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark"))

  @Before
  def start(): Unit = {
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path, true)
  }

  @After
  def stop(): Unit = {
    sc.stop()
  }

  /**
   * 广播变量
   */
  @Test
  def test01()={
    val list = List(1, 2, 3, 4, 5, 6)
    val rdd = sc.makeRDD(list)
    val range = Range(2, 20000)
    // 使用广播器
    val br = sc.broadcast(range)
    // 判断当前rdd中那些元素在range中出现过,出现的元素进行保存
    // range只读
    // RDD是分布式的会发给其他的服务器结点 也即是executor进行运算
    // 如果只是用了range普通变量 会导致每个Task传一份Range数据,这样会大大地增加了数据的传输IO,网络IO
    //val rdd2 = rdd.filter(x => range.contains(x))
    // 使用了广播变量后 driver端只需把range传给需要用到range的结点(executor),executor
    // 把数据存储在本机,那个task需要range变量就在本机取,其他的executor如果也需要可以从同一机架的就近的
    //其他的executor上取的广播变量,或者从driver上取
    val rdd2 = rdd.filter(x => br.value.contains(x))
    rdd2.foreach(println)
  }

  /**
   * 累加器
   */
  @Test
  def test02()={
      var list=List(1,2,3,4,5,6)
      val rdd = sc.makeRDD(list)
      var sum=0
      // sum+=x的运算是发给executor进行计算的,是把sum的一个副本赋值给了executor
      // sum副本和driver没有关系,所以sum还是等于0
      rdd.foreach(x=>sum+=x)
      println(sum) //0

  }

  /**
   *使用累加器
   */
  @Test
  def test03()={
    var list=List(1,2,3,4,5,6)
    val rdd = sc.makeRDD(list)
    // 使用累加器longAccumulator
    // 先把longAccumulator变量复制给所有的executor,
    // executor端会对longAccumulator进行清零操作
    // executor运算完后
    // 把累计器变量返回给driver,在driver端在进行merge,统计
    val sumLongAccumulator = sc.longAccumulator("sum")
    rdd.foreach(x=>sumLongAccumulator.add(x))
    println(sumLongAccumulator.value) //0

  }

  /**
   * 自定义累加器
   * 在executor上不能读取累加器的值,只能在driver端获取累加器的值
   * 可以通过extends.AccumulatorV2实现自己的累加器
   * 使用SparkContext.register(自定义累加器)
   * 调用的流程 :每个task
   *      copy()-->reset-->isZero-->序列化
   *      通俗来说就是:每个task都先把累加器复制一份 然后把累加器重置为零 再次确认累加器是否为零
   */
    @Test
  def test04()={

    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list,2)
    // 注册自定义的累加器
    val myIntAccumulator = new MyIntAccumulator()
    sc.register(myIntAccumulator)
    rdd.foreach(x=>{
      myIntAccumulator.add(x)
    })
    println(myIntAccumulator.value)// 20

  }

  @Test
  def test05()= {
    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list)
    val myIntAccumulator = new MyIntAccumulator01()
    // 注册累加器
    sc.register(myIntAccumulator)
    rdd.foreach(myIntAccumulator.add)
    println(myIntAccumulator.value) //30
  }

  /**
   * WordCount 进行统计
   */
  @ Test
  def test06()={
    val rdd = sc.textFile("input/1.txt")
    // 注册累加器
    val wordCountAccumulator = new WordCountAccumulator
    sc.register(wordCountAccumulator) // 注意要记得注册累加器

    rdd.flatMap(_.split(" ")).foreach(wordCountAccumulator.add)
    println(wordCountAccumulator.result)
  }

  @Test
  def test07()={
    val rdd = sc.textFile("input/1.txt")
    // 注册自定义累加器
    val wordCountAccumulator= new WordCountAccumulator01
    sc.register(wordCountAccumulator)
    rdd.flatMap(_.split(" ")).foreach(wordCountAccumulator.add)
    println(wordCountAccumulator.value)
  }
}


class WordCountAccumulator extends AccumulatorV2[String,mutable.Map[String,Int]] {

 var result = new mutable.HashMap[String, Int]()
  // 判断是否为空
  override def isZero: Boolean = result.isEmpty
  // 复制累加器
  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = new WordCountAccumulator
  // 重置为空
  override def reset(): Unit = result.clear()
  // 相同key值的value加1
  override def add(v: String): Unit = result.put(v,result.getOrElse(v,0)+1)

  // 每个task的累加器汇总到driver端进行合并
  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    val mergeValue = other.value
    // 遍历Map 获取key和value
    for ((key,value)<-mergeValue){
      result.put(key,result.getOrElse(key,0)+value)
    }
  }

  // 返回结果
  override def value: mutable.Map[String, Int] = result
}

/**
 * 自定累加器
 * @param num
 */
class MyIntAccumulator01(num:Int=20)extends AccumulatorV2[Int,Int] {
  var result:Int=num
  // 判断是否归零
  override def isZero: Boolean = result==0
  // 复制累加器
  override def copy(): AccumulatorV2[Int, Int] = new MyIntAccumulator01
  // 重置累加器为零
  override def reset(): Unit =  result=0
  // 相加操作
  override def add(v: Int): Unit = result+=v
  // 把每个executor上相同类型的累加器合并
  override def merge(other: AccumulatorV2[Int, Int]): Unit = result+=other.value
  // 把合并后的累加器返回
  override def value: Int = result
}


/**
 * 自定义累加器
 *    继承AccumulatorV2[IN, OUT] 一个数输入参数类型,一个是输出参数类型
 *
 */
class MyIntAccumulator(num:Int=10) extends AccumulatorV2[Int,Int] {
  // 用来保存累加的值
  var result:Int=10
  // 是否归0
  override def isZero: Boolean = result==0
  // 返回当前累计器的一个副本
  override def copy(): AccumulatorV2[Int, Int] = new MyIntAccumulator
  // 重置累加器归0
  override def reset(): Unit = result=0
  // 累加
  override def add(v: Int): Unit = result+=v
  // 合并相同类型的累加器的值
  override def merge(other: AccumulatorV2[Int, Int]): Unit = result+=other.value
  // 获取累加的值
  override def value: Int = result
}

class WordCountAccumulator01 extends AccumulatorV2[String,mutable.Map[String,Int]] {

  var result = new mutable.HashMap[String, Int]()

  override def isZero: Boolean = result.isEmpty

  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = new WordCountAccumulator01

  override def reset(): Unit = result.clear()

  override def add(v: String): Unit = result.put(v,result.getOrElse(v,0)+1)

  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    val mergerValue = other.value
    for ((key,value)<-mergerValue){
      result.put(key,result.getOrElse(key,0)+value)
    }
  }

  override def value: mutable.Map[String, Int] = result
}