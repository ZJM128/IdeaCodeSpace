package com.atguigu.day01

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.{SPARK_BRANCH, SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 自定义接收器
 * 总结:
 * 定义接收器
 * [1]继承Receiver[T](StorageLevel.MEMORY_ONLY)抽象类,因为Receiver是带参数的抽象类,所以在继承Receiver的时候需要传入参数StorageLevel.MEMORY_ONLY
 *    该参数表示接收的数据暂时存放在什么地方(内存,磁盘等....),还需要指定Receiver的泛型类型,来规定接收的数据是以什么数据类型存在内存中store(T)
 * [2]重写onStart和onStop
 *      (1)在onStart中
 *        ①创建读取数据的环境(比如与网络通讯的Socket和读取数据的BufferedReader),如果与网络连接不通需要重连(调用restart())
 *        ②开启一条守护线程进行数据的读取,因为Receiver(采集器)是长期运行的,所以在该线程中需要使用while(条件)对数据进行读取
 *      (2)onStop对onStart中初始化的环境进行资源的回收,清理
 * 在采集器中使用自定义接收器
 *    [1]创建接收器
 *    [2]通过streamingContext.receiverStream(myReceiver) 创建DStream
 *
 *
 */
object $03_CustomReceiver {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark")
    // 创建SparkStreaming的运行环境, 指定采集数据周期为3秒
    val streamingContext = new StreamingContext(sparkConf, Seconds(3))

    // 创建自定义接收器
    val myReceiver = new MyReceiver("hadoop103", 4444)
    //通过自定义的接收器 获取DStream
    val ds = streamingContext.receiverStream(myReceiver)
    // ds中的方法称之为高度抽象原语
    val result: DStream[(String, Int)] = ds.flatMap(line => line.split(" ")).map((_, 1)).reduceByKey(_ + _)
    // 打印
    result.print(1000)

    // 启动Receiver 接收器
    streamingContext.start()
    // 阻塞档前的线程 一直到Receiver 结束或出现异常
    streamingContext.awaitTermination()

  }

}

/**
 * 模拟 wordCount中 读取hadoop103:4444 端口发送的文本数据
 */
class MyReceiver(host:String,port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {
  var socket:Socket=null
  var reader:BufferedReader=null
  /**
   * 接收数据需要的组件:
   *    Socket:连接的端口
   *    BufferedReader :一次读取一行的数据
   * 接收数据之前,在onStart进行安装,在停止收数据时,在onStop清理
   */
  override def onStart(): Unit = {
    try {
      socket = new Socket(host, port)
    } catch {
      case exception: Exception =>
        restart("重新连接") // 有异常
        return // 需要return来阻止代码继续运行
    }

    // 如果前面不出异常 则进可以数据进行读入
    println("已经连接")
    reader= new BufferedReader(new InputStreamReader(socket.getInputStream, "utf-8"))

    // 先开启一个线程去接收数据
    receiverData()


  }

  // 接收数据的方法
  def receiverData() ={

    new Thread(){

      // 设置为守护线程
      // 问:为什么亚设置守护线程 答:因为主线程(streamingContext) 如果已经停止了,也就没有必要接收数据了
      setDaemon(true)
      // 接收数据的逻辑
      override def run(): Unit = {
        // reader.readLine() 如果读不到数据就阻塞 要么阻塞 要么就读到了数据,line不为null 有内容
        var line = reader.readLine()
        try {
          // 此处:line不会为null 也就是结束循环只有socket连接出异常了
          while (socket.isConnected && line != null) {
            // 存储读到的数据
            store(line)
            // 继续读取下一行
            line = reader.readLine()
          }
        } finally{
          // 问:问什么没有catch 答:出异常后,我们不需要报错信息,而是需要自动重新连接,所以不需要catch
          // 清理本次安装的组件
          onStop()
          // 再次重新连接
          restart("重新连接")
        }
      }
    }.start()
  }


  /**
   * 在停止收数据时,在onStop回收资源
   */
  override def onStop(): Unit ={
    if (!socket.isClosed) {
      socket.close()
    }
      if (reader!=null){
        reader.close()
        reader==null
      }

  }
}