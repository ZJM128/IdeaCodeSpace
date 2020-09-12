package com.atguigu.Day01

import java.io.{OutputStreamWriter, PrintWriter}
import java.net.Socket

object IO_Client {
  def main(args: Array[String]): Unit = {

    def testService(): Unit = {
      var client = new Socket("localhost",6666)
      var out = client.getOutputStream
      var writer=new PrintWriter(new OutputStreamWriter(out))
      writer.println("hello Scala")
      writer.flush()
      writer.close()
      client.close()
    }

    testService()
  }
}
