package com.atgugui.day02


import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 分区规则
 */
class $02_CreatePartition {

  private val context = new SparkContext(new SparkConf().setMaster("local[3]").setAppName("my_spark"))

  @Before
  def start(): Unit ={
    val fileSystem = FileSystem.get(new Configuration())
    val path = new Path("outputText")
    if (fileSystem.exists(path))
      fileSystem.delete(path,true)
  }
  @After
  def stop(): Unit ={
    context.stop()
  }

  /**
   * 创建内存RDD的分区数
   *  (1)默认分区数
   * override def defaultParallelism(): Int =
   *    scheduler.conf.getInt("spark.default.parallelism", totalCores)
   *    totalCores:总cpu核数,和new SparkConf().setMaster("local[2]").setAppName("my_spark")的local中的数量有关
   *    默认的核数是:1,
   *    local[数字]:数字多少就是多少核数,
   *    local[*]:为主机的全部cpu核数
   *    "spark.default.parallelism" 默认没有设置值
   *
   * defaultParallelism=totalCores (当前本地集群可以用的总核数) 目的为了最大限度并行运行
   *     standalone/YARN模式.totalCores是Job申请的总的核数
   *
   * 数据底层分区原理:
   *  (1)首先对numSlice也就是分区的个数进行判断,如果小于零 则报错
   *    if (numSlices < 1) {
   *        throw new IllegalArgumentException("Positive number of partitions required")
   *     }
   * (2)对传进来的数据结构进行判断,如果不是Range的话就走case_=>逻辑
   * seq match {
   *    case r: Range =>
   *    case _ =>
   *  val array = seq.toArray // To prevent O(n^2) operations for List etc
   *    positions(array.length, numSlices).map { case (start, end) =>
   *      array.slice(start, end).toSeq
   *    }.toSeq
   *  }
   *  (3)调用positions(array.length, numSlices)方法,出入数组的长度和分区个数
   *    def positions(length: Long, numSlices: Int): Iterator[(Int, Int)] = {
   *      (0 until numSlices).iterator.map { i =>
   *      val start = ((i * length) / numSlices).toInt
   *      val end = (((i + 1) * length) / numSlices).toInt
   *      (start, end)
   *    }
   * }
   * (4)positions(length: Long, numSlices: Int)方法中对数组中的数据的索引进行分区,分区的规则如下
   *       (0 until numSlices).iterator.map { i =>
   *           val start = ((i * length) / numSlices).toInt
   *           val end = (((i + 1) * length) / numSlices).toInt
   *           (start, end)
   *   ①遍历0到分区数(不包含分区数)
   *   ②start索引取(i+数组长度)/分区数
   *    end索引取((i+1)+数组)/分区数
   *   ③遍历完成返回元组集合
   *(5)调用array.slice(start, end).toSeq方法
   *    slice(start, end)返回数组中索引在[start,end)的数据
   *
   * 总结:ParallelCollectionRDD在对集合中的元素进行分区时,大致是平分的.
   *    如果不能整除,后面的分区会分多一个
   */
    @Test
  def createPartitionByMemory(): Unit ={
    val rdd = context.makeRDD(List(2, 3, 5, 2, 5))
    rdd.saveAsTextFile("output")
  }

  /**
   * 从外部存储（文件）创建RDD的分区数
   * def textFile(
   *    path: String,
   *     minPartitions: Int = defaultMinPartitions): RDD[String] = withScope {
   *     assertNotStopped()
   *     hadoopFile(path, classOf[TextInputFormat], classOf[LongWritable], classOf[Text],
   *     minPartitions).map(pair => pair._2.toString).setName(path)
   * }
   * (1)默认的分区数 使用defaultParallelism(默认集群的核数)和2取最小
   * def defaultMinPartitions: Int = math.min(defaultParallelism, 2)
   *
   * 底层分区原理
   *(1)根据输入的格式（org.apache.hadoop.mapred.TextInputFormat）进行切片
   *    [1]先计算全部文件的总大小
   *    totalSize += file.getLen();
   *    [2]计算取期望每片大小goalSize,numSplits为设置的分区数或默认的分区数(2)
   *     long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);
   *    [3] 以每个文件为单位进行切片
   *   {1}判断文件的大小是否大于0
   *    Ⅰ判断文件是否可以进行切片 isSplitable(fs, path) 可以则走下面逻辑
   *      <1> 获取文件的块大小,块大小在上传文件时指定,如果不指定,默认为128M 本地32M
   *       long blockSize = file.getBlockSize();
   *      <2>计算切片大小,在大数据的情况下 一般是128M,也就是块的大小,因为dn中的块大小也是128M
   *      long splitSize = computeSplitSize(goalSize, minSize, blockSize);
   *      <3>把文件的大小赋值给一个临时变量
   *       long bytesRemaining = length
   *       <4>循环切片:判断当前的bytesRemaining的大小除以切片的大小是否大于1.1 ((double) bytesRemaining)/splitSize > SPLIT_SLOP
   *          ①大于:进行分切 bytesRemaining -= splitSize,然后进行循环切片
   *          ②小于不进入循环,走下面的逻辑
   *        <5>剩余部分 <=片大小1.1倍，整体作为1片
   *     Ⅱ文件不可以切片:整个文件作为1片
   *   {2}文件大小为0 则创建一个空切片
   *
   *(2) 是否过滤空切片后的切片集合 并且把切片数赋值给分区数
   * val inputSplits = if (ignoreEmptySplits) {
   *    allInputSplits.filter(_.getLength > 0)
   * } else {
   *    allInputSplits
   * }
   *(3)如果切的是1片，且是针对文件的切片，做特殊处理
   *  if (inputSplits.length == 1 && inputSplits(0).isInstanceOf[FileSplit])
   * (4)声明长度为切片数的数组
   *  val array = new Array[Partition](inputSplits.size)
   *  循环取得分区数据
   *  for (i <- 0 until inputSplits.size) {
   *      array(i) = new HadoopPartition(id, i, inputSplits(i))
   *  }
   *   array
   * (4)结论:defaultParallelism或minPartitions的分区数不能是最终的分区数,但会影响最终的分区数
   *     long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);
   */
  @Test
  def createPartitionByFile(): Unit ={
    val rdd = context.textFile("input")
    rdd.saveAsTextFile("outputText")
  }

  /**
   * slice 根据索引获取数组中的数据
   */
  @Test
  def test01()={
    val list = List(1, 2, 3, 4)
    println(list.slice(0, 2))
  }
}
