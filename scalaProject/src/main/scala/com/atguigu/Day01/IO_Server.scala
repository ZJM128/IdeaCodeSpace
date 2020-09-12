package com.atguigu.Day01

import java.io.{BufferedReader, InputStreamReader}
import java.net.ServerSocket
/**
 * 网络IO
 * Scala中的网络数据交互,也是采用java中的IO类
 */
object IO_Server {
  def main(args: Array[String]): Unit = {

    def testClient(): Unit ={
      val server=new ServerSocket(6666)
      var socket=server.accept()
      val read=new BufferedReader(new InputStreamReader(socket.getInputStream))
      var str=""
      var flag=true
      while(flag){
        str=read.readLine()
        if (str!=null){
          println(str)
        }else{
          flag=false
        }
      }
    }
    testClient()
  }


}
