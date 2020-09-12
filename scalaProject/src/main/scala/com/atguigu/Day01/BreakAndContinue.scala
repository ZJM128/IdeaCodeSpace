package com.atguigu.Day01
import scala.util.control.Breaks._
/**
 * scala中实现break和continue功能
 * 1 导包: import scala.util.control.Breaks._
 * 2 通过breakable与break方法实现continue与break方法功能
 * 1,实现continue
 * for(){
 *  breakable{
 *    if(..)break()
 *  }
 * }
 *
 * 2 实现break
 * breakable{
 * for(..){
 *  if(...)break()
 * }
 * }
 */
object BreakAndContinue {
  def main(args: Array[String]): Unit = {
    def continueTest(): Unit ={
      // continue
      for(i<-1 to 10){
        breakable{
          if (i==2)break()
          println(i)
        }
      }
    }
    def breakTest(): Unit ={
      breakable{
        for (i<- 1 to 10){
          if (i==5)break()
          println(i)
        }
      }
    }
    //breakTest()
    breakable(
      for (i<- 1 to 10){
      if (i==5)break()
      println(i)
    })
  }
}
