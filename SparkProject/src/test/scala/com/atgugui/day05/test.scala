package com.atgugui.day05

import org.apache.spark.{SparkConf, SparkContext}

object test {

  def main(args: Array[String]): Unit = {
    //(zhang,san)
    //(zhang,san)
    //hello("zhang","san"),("zhang","san")


  }
  def hello(name:((String,String),(String,String))){
    println(name._1,name._2)
  }

}
