package com.atguigu.Day01

/**
 * scala的for循环有点类似java的for增强for循环
 * to方法:左右闭合
 *  调用方式:
 *    startIndex.to(endIndex)
 *    startIndex to endIndex
 * until:左闭右开
 *    调用方式
 *       startIndex.until(endIndex)
 *       startIndex until endIndex
 *  scala中方法调用两种方式
 *    1,对象.方法(参数)
 *    2,对象 方法(参数)// 如果参数只有一个,()可以省略
 *
 *    for循环基本语法
 *       for(变量名<- 表达式/集合/数组){}
 *    守卫:
 *       每次循环之前进行判断 for(变量名 <- 表达式/集合/数组 if(boolean表达式)
 *    嵌套for循环:
 *        for(变量名 <-表达式/集合/数组;变量名<- 表达式/集合/数组){}
 *
 *    for步长
 *      for(变量名< startIndex to (endIndex,步长))
 *      for(变量名< startIndex to endIndex by 步长)
 *      for(变量名< Range(startIndex,endIndex,步长))
 *   要想for循环有返回值,必须通过yeild表达式
 *
 *
 */
object For_Demo {
  def main(args: Array[String]): Unit = {

    // to 左右闭合
    def ToTest(): Unit ={
      for (i<-0 to 10){
        println(i)
      }
    }
    // until 左闭右开
    def nutilTest(): Unit ={
      for (number<-0 until 10){
        println("*"*number)
      }
    }
   // NutilTest()

    //守卫:
    def protectTest(): Unit ={
      for (i<- 0 to (10,2) if (i%2==0)){
        println(i)
      }
    }
    //protectTest()

    // 嵌套for循环
    def forAndFor(): Unit ={
      for (number<-1 until  10){
        for(j <-1 to number) {
          print(s"${j}*$number=${j * number}")
          print("\t")
        }
        println()

      }
    }
   // forAndFor()
    def forAndFor2(): Unit ={
      for (number<-1 until  10;j <-1 to number){
          print(s"$j*$number=${number*j}\t")
        if (j==number){
          println()
        }
      }
    }
    //forAndFor2()

    def forAndFor3(): Unit ={
      for (i<-1 to 9 if (i%2==0);j<-1 to i ){
        println(s"$i*$j=${i*j}\t")
      }
    }
    forAndFor3()

    // for循环有返回值
    def forAndResult(): Unit ={
      val result=for (i<- Range(0,10,2))yield {
        println(i)
        i
      }
      println(s"返回值$result")
    }
    //forAndResult()


    /**
     * Range和until一样,左闭右开
     */
    for(i<- Range(0,10))print(i+"\t")
    println()
    for (i<- 0 to 10)print(i+"\t")
    println()
    for (i<-0 until 10)print(i+"\t")
  }
}
