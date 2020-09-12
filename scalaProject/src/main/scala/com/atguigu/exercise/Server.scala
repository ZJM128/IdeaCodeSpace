package com.atguigu.exercise

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.ServerSocket

import com.atguigu.summerframework.bean.Task
import com.atguigu.summerframework.code.Application

object Server  extends Application{
  def main(args: Array[String]): Unit = {
    start("serverSocket"){
      // 获取serverSocket
     var serverSocket= envData.asInstanceOf[ServerSocket]
      while(true){
        // 获取客户端
       var socket=serverSocket.accept()
        new Thread(new Runnable {
          override def run(): Unit = {
            // in 读取客户端的业务逻辑代码
            var ois:ObjectInputStream=new ObjectInputStream(socket.getInputStream)
            var task=ois.readObject().asInstanceOf[Task]
            var result=task.compute()
            socket.shutdownInput()
            // out 结果返回给客户端
            var oos:ObjectOutputStream=new ObjectOutputStream(socket.getOutputStream)
            oos.writeObject(result)
            oos.flush()
            oos.close()
            if (!socket.isClosed)socket.close()
            socket=null
          }
        }).start()

      }
    }
  }

}
