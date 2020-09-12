package com.atgugui.day05

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * Cache缓存：  缓存为了提高对重复元素的查询效率
 *
 * 为了提高查询效率，多使用内存作为缓存设备！
 * 优秀的缓存： Ehcache, MemCache , Redis
 * 在Spark中可以将RDD进行缓存！ 对于重复计算的步骤，可以直接从缓存中取出已经计算好的RDD！
 * 缓存都会有数据的淘汰策略：
 * LRU（less recent use）: 优先淘汰最近最少使用的数据
 * TTL ： 可以在每个数据写入缓存时，指定数据的过期时间，优先淘汰快要过期的数据，ttl()返回值小
 * Random : 随机淘汰
 * 在Spark中，使用 LRU策略！
 * 在Spark中，可以通过 persist(StorageLevel)来指定缓存的设备！ 优先推荐使用内存作为缓存！
 * 缓存不会改变RDD的血缘的关系！ 原因在于缓存多使用内存，因此不可靠，在容错时，如果缓存中没有可以使用的RDD，就
 * 需要根据血缘关系重新计算！
 * Mybatis: ORM(object relational mapping)框架
 * ORM： 对象关系映射
 * 将一个Java中的对象， 通过方法，将对象中的数据写入到关系(数据库中的表)中
 * Java： new Person("Tom","male",20)
 * Mysql :   表中的一条记录
 * Java用来作为后端语言，开发后端应用！
 * 通过html页面----->点击按钮----->向后端应用发送http请求----->处理请求
 * ----->验证页面表单中填写的数据是否正确
 * ----->  将http请求中携带的用户名，密码参数 ---->封装的JavaBean(数据模型)
 * ------>  调用方法    validUser(Person("Tom","male",20) )
 * ------>  从数据库中，根据Person中的属性进行查询，将查询出的记录 封装为
 * Person("Tom","male",30)
 * 将两个Person对象，进行比较，确定用户传入的参数是否正确！
 *
 * 从数据库  查询
 * 写入数据库
 *
 *
 * checkpoint:  检查点。
 * 因为缓存的不可靠，所以Spark提供了对RDD的另外一种持久化方式，允许将RDD通过ck持久化到文件系统中！
 * SparkContext#setCheckpointDir: 设置ck目录,如果是集群模式，必须是HDFS上的路径！
 * 会删除当前RDD之前的血缘关系！不会对容错机制产生影响！
 * checkpoint 会在 RDD的第一个行动算子执行时执行！
 * 在使用checkpoint时，结合使用cache，避免checkpoint的Job重复计算！
 *
 */
class $02_Cache {

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
   * 将rdd进行缓存
   */
  @Test
  def test01() = {
    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list)
    val rdd1 = rdd.map(x => {
      println("max")
      x
    })
    // 将rdd2进行缓存
    rdd1.cache()
    // cache() 等价于 persist() ==>persist(StorageLevel.MEMORY_ONLY)
    rdd1.saveAsTextFile("output")
    println("&*" * 20)
    rdd1.foreach(println)
  }

  @Test
  def test02() = {
    val list = List(1, 2, 3, 4)
    val rdd = sc.makeRDD(list)
    val rdd1 = rdd.map(x => {
      println("max")
      (x,1)
    })
    // 当我们使用了会产生shuffle的算子,shuffle阶段MapTask的结果会自动缓存(磁盘)
    val rdd2 = rdd1.reduceByKey(_ + _)
    println(rdd2.collect().mkString(","))

    // 直接读取shuffle写出的文件数据,跳过shuffle的map阶段
    rdd2.saveAsTextFile("output")
    Thread.sleep(1000000)
  }
}
