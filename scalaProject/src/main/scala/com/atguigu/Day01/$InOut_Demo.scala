package com.atguigu.Day01

import java.io.{BufferedReader, InputStreamReader, OutputStreamWriter, PrintWriter}
import java.net.{ServerSocket, Socket}

import scala.io.{Source, StdIn}


object $InOut_Demo {
  def main(args: Array[String]): Unit = {

  }
  def test01(): Unit ={
    // 控制台输入数据
    // var line=StdIn.readLine("请输入数据:")
    // println(line)

    // io流读数据
    var source=Source.fromFile("D:/code/flume-kafka.sh", "utf-8")
    source.getLines().foreach(println(_))
    source.close()
  }
  /*
   控制台输入
   */
  def test02(): Unit ={
    var line=StdIn.readLine("请输入数据")
    println(line)
  }
  /**
   * 从文件中获取输入
   */
  def test03(): Unit ={
    val source=Source.fromFile("D:/code/flume-kafka.sh","utf-8")
    source.getLines().foreach(println(_))
    source.close()
  }
  /**
   * 输出
   * scala进行文件的写操作,用的都是java中的IO类
   */
  def test04(): Unit ={
    // 获取流对象
   val writer=new PrintWriter("D:/code/helloScala.txt")
    // 对数据进行操作
    writer.write("hello Scala")
    // 关闭流
    writer.close()
  }


}
