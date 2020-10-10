package com.atguigu.day01

/**
 * 守护线程
 *   如果jvm只剩守护线程 此时jvm就会关闭
 *
 *   (1)在main方法中启动一条线程,如果不是守护线程
 *   那么就算main方法结束了,启动的线程也不会因为main
 *   方法的结束而结束,而是运行完自己的逻辑后,自动停止
 *   (2)如果是守护线程,那么一旦main方法结束,守护线程
 *   也会结束运行
 */
object DaemonThreadTest {

  def main(args: Array[String]): Unit = {

    val thread = new Thread(new myThread)
    // 设置为守护线程
    thread.setDaemon(true)
    // 启动线程
    thread.start()
    Thread.sleep(500)
    println("main线程over")
  }

}
class myThread extends Runnable{
  override def run(): Unit = {
    for (i<-1 to 10){
      println(Thread.currentThread().getName +" ....."+i)
      Thread.sleep(500)
    }
  }
}