package com.atguigu.Day01

object For_Demo1 {
  def main(args: Array[String]): Unit = {
    // 守卫
   // for (i<-1 to 10 if i<5)println(i)

    //  步长
    //for (i<-Range(1,10,2))println(i)
    //for (i<-2 to 10 by 2)println(i)
    //for (i<-2 to(10,2))println(i)

    // 嵌套
    /*for (i<- 1 to 10;j<-1 to 4){
      println(s"$i*$j=${i*j}")
    }
    println("="*20)
    for (i<-1 to 10){
      for (j<-1 to 4){
        println(s"$i*$j=${i*j}")
      }
    }*/

   /* for (i<-Range(1,5);j=i-1){
      println(s"j=$j")
    }*/
/*
    for(i<-1 to 9){
      print("\t"*(9-i))
      println("*\t"*i)
    }*/

    // 返回值
    var result=for (i<-1 to 9)yield {
      i
    }
    println(result)

  }
}
