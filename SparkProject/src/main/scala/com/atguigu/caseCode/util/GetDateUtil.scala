package com.atguigu.caseCode.util

import com.atguigu.caseCode.bean.UserVisitAction
import com.atguigu.summerframework.code.Application
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

class GetDateUtil{

  def getFileDateToBean(pathName:String)={

     /* val sc = envData.asInstanceOf[SparkContext]
      val rdd = sc.textFile(pathName)
        rdd.map(line => {
        val arr = line.split("_")
        UserVisitAction(arr(0), //用户点击行为的日期
          arr(1).toLong, //用户的ID
          arr(2), //Session的ID
          arr(3).toLong, //某个页面的ID
          arr(4), //动作的时间点
          arr(5), //用户搜索的关键词
          arr(6).toLong, //某一个商品品类的ID
          arr(7).toLong, //某一个商品的ID
          arr(8), //一次订单中所有品类的ID集合
          arr(9), //一次订单中所有商品的ID集合
          arr(10), //一次支付中所有品类的ID集合
          arr(11), //一次支付中所有商品的ID集合
          arr(12).toLong //城市 id
        )
      })
    */
  }
}
