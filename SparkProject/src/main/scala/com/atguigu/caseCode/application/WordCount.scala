package com.atguigu.caseCode.application

import com.atguigu.caseCode.util.GetDateUtil
import com.atguigu.summerframework.code.Application
import org.apache.spark.SparkContext

object WordCount  extends Application{

  private val getDateUtil = new GetDateUtil()
  var pathName="output"
  def main(args: Array[String]): Unit = {
    /*start("spark"){
      val sc = envData.asInstanceOf[SparkContext]
     val rdd = sc.textFile("input/1.txt")
      val rdd2 = rdd.flatMap(_.split(" ").map((_, 1))).reduceByKey(_+_)
      rdd2.saveAsTextFile(pathName)
    }(pathName)*/


  }
}
