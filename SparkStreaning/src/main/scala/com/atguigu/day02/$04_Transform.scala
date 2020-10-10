package com.atguigu.day02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 将一个DS转换成另一个DS
 * --1. 方法：transform[U: ClassTag](transformFunc: RDD[T] => RDD[U]): DStream[U]
 * --2. 泛型：可以指定一个泛型，这个泛型则规定了转换后的RDD和DStream的类型
 * --3. 形参：是一个函数：transformFunc
 *          函数的形参：每个采集周期生成的RDD；
 *          函数的返回值：RDD[U]，返回一个RDD，RDD的数据类型和指定的泛型一致。
 * --4. 操作的作用：
 *    每个采集周期生成的RDD都会调用一次这个操作。主要是进行RDD的转换，在转换的过程，可以对数据进行处理。
 * --5. 什么时候使用？
 * 与采集周期相关的时候使用，这里主要的因为代码执行的次数有关
 *        //code1
 *            transform(rdd => {
 *            // code2
 *            rdd.flatMap{
 *            // code3
 *           }
 *        )
 *
 * "说明"：
 * code1：在driver中执行，只会执行1次
 * code2：每一个采集周期会生产一个RDD，每生成一个RDD会执行一次
 * code3：每一个RDD中的数据，会执行一次
 *
 * transform和foreach的区别
 * (1)transform的目的是将一个DS中的RDD处理后转为另一个RDD返回称为新的DS
 *    foreach 遍历每个RDD 队每个RDD中的元素进行处理
 * (2)transform 有返回值
 *    foreachRDD没有返回值
 *
 *
 */
object $04_Transform {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("mu")
    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    val ds = streamingContext.socketTextStream("hadoop103", 8888)
    // 在driver端执行一次
    // code
    val ds1 = ds.transform(
      // 在driver端 每生成一个RDD 自行一次
      // code2
      rdd =>
        rdd.map(x =>
          // 在executor端执行 RDD中每个元素都会执行一次
          // code
          (x, 1)
        ))
  }
}
