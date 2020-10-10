package com.atguigu.day02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 如何优雅的把streamingContext停止
 * 1,分类
 *  (1)粗鲁的:直接在yarn界面直接kill掉streamContext的线程
 *  (2)优雅的:调用streamingContext.stop(true,true)=>stop中传入两个参数,其作用是:如果还有job没有完成的话
 *    该stop会等待该job完成后才进行stop
 *  2,实现
 *    (1)开启一个线程,在分线程中,需要关闭应用是就关闭
 *    (2)在run方法中写一个判断应用是否要关闭的方法,获取需要关闭信息的数据可以存在mysql数据库或HDFS文件系统中
 *    (3)在run方法中使用while(判断方法)来周期性判断判断方法的状态
 *    (4)当while不满足的时候就执行streamingContext.stop(true,true)方法进行优雅的关闭
 */
object $02_StopGracefully {

  def main(args: Array[String]): Unit = {
    val myConf = new SparkConf().setMaster("local[*]").setAppName("my")
    // 周期为3秒一个周期
    val streamingContext = new StreamingContext(myConf, Seconds(3))
    // 高度原语 采集数据
    val ds = streamingContext.socketTextStream("hadoop103", 8888)
    //处理数据的逻辑
    val ds1 = ds.flatMap(line => line.split(" ").map(word => (word, 1))).reduceByKey(_+_)
    // 打印数据
    ds1.print(1000)

    // 开启一个线程,在分线程中需要关闭应用是就关闭
    new Thread(){
      override def run(): Unit = {
        // 函数是来获取是否需要关闭的
        def ifShouldStop(): Boolean ={
          // 这个boolean值可以通过读取数据库中的某一个值,或者读取HDFS上的某个文件中的值
          true
        }
        // 不断地获取是否需要关闭的信息
        while(!ifShouldStop()){
          // 停止一会,再去检查是否需要关闭
          Thread.sleep(500)
        }
        // 关闭应用,stopGracefully 等最后一批收到的数据全部处理完关闭应用
        streamingContext.stop(stopSparkContext = true,stopGracefully = true)
      }
    }
    // 启动应用
    streamingContext.start()
    // 阻塞当前线程 ,持续周期性采集数据
    streamingContext.awaitTermination()

  }

}
