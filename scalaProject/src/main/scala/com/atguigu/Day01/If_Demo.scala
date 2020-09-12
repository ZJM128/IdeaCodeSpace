package com.atguigu.Day01

/**
 * scala
 * 1 单个if
 * 2 if else
 * 3 if-else if-else
 * 都是有返回值的,if-else的返回值就是满足条件的一个分支的块表达式[{}最后一行表达式的结果值]的结果值
 *
 */
object If_Demo {

  def main(args: Array[String]): Unit = {
    // 单个if
    def testIf(): Unit ={
      val number=30
      var result=if (number%2==0){
        number
      }
      println(result)
    }

    // if-else
    def testIfElse(): Unit ={
      var number=30
     var result=if (number>60){
        number-90
      }else {
        number*4
      }
      println(result)
    }
    //testIfElse()

    // if-else if-else
    def testIfElseIfElse(): Unit ={
      val number =20
      val result=if (number>10){
        "10-30"
      }else if (number>30){
        "30-~"
      }else{
        "小于10"
      }

      println(result)
    }
    //testIfElseIfElse()

    // 如果没有最后一行的话 即返回()
    val number=90
    val result=if (number>20){
      if (number%3!=0){
        number*2
      }
    }
    println(result)
  }
}
