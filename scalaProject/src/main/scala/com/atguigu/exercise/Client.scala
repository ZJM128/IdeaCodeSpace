package com.atguigu.exercise

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigu.summerframework.bean.Task
import com.atguigu.summerframework.code.Application

object Client extends Application{

  def main(args: Array[String]): Unit = {
    start("socket"){
      val socket= envData.asInstanceOf[Socket]
      // out 把业务逻辑传给服务端
      val oos:ObjectOutputStream=new ObjectOutputStream(socket.getOutputStream)
      val task:Task=new Task
      task.data=4
      task.logic=(i:Int)=>{i*20}
      //task.logic=_+20
      oos.writeObject(task)
      // 关闭输出流
      socket.shutdownOutput()
      // int 从服务端读取结果
      var ois=new ObjectInputStream(socket.getInputStream)
      var result=ois.readObject()
      println("结果:"+result)
      ois.close()
    }
  }

}
