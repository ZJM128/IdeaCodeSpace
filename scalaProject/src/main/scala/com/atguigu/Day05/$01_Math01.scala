package com.atguigu.Day05

import scala.io.StdIn

/**
 * 模式匹配语法
 *    变量 match {
 *      case 值 =>
 *        匹配后的逻辑
 *      case 值 =>
 *         匹配周的逻辑
 *      case _ =>
 *          都不匹配的逻辑
 *    }
 */
object $01_Math01 {

  def main(args: Array[String]): Unit = {
    val word = StdIn.readLine()
    word match {
      case "李白"=>println(s"$word")
      case "hadoop"=>println(s"$word")
      case _=>println("无法匹配")
    }
  }
}
