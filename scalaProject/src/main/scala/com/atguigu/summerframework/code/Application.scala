package com.atguigu.summerframework.code

import java.net.{ServerSocket, Socket}

import com.atguigu.summerframework.util.PropertiesUtil

class Application {
  var envData:Any=null

  def start(s:String)(op: =>Unit): Unit ={

    // 1 初始化环境
   // println(s"$s 环境的初始化成功")
     if (s=="socket"){
       envData=new Socket(PropertiesUtil.getValue("server.host"),PropertiesUtil.getValue("server.port").toInt)
     }else if (s=="serverSocket"){
       envData=new ServerSocket(PropertiesUtil.getValue("server.port").toInt)
     }else{
       throw new RuntimeException("参数传入不正确")
     }
    // 2 业务逻辑
    try{
      op
    }catch {
      case exception: Exception=>println("业务逻辑执行失败"+exception.getMessage)
    }

    // 3环境的关闭
   // println(s"$s 环境关闭")
    if (s=="socket"){
      var socket=envData.asInstanceOf[Socket]
      if (!socket.isClosed){
        socket.close()
      }
    }else if (s=="serverSocket"){
      var serverSocket=envData.asInstanceOf[ServerSocket]
      if (!serverSocket.isClosed){
        serverSocket.close()
      }
    }
  }
}
