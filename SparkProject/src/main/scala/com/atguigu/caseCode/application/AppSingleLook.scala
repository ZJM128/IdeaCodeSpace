package com.atguigu.caseCode.application

import java.text.DecimalFormat

import com.atguigu.caseCode.application.AppHotCategoryAnalysisTop10Two.envData
import com.atguigu.caseCode.bean.UserVisitAction
import com.atguigu.summerframework.code.Application
import org.apache.spark.{SPARK_BRANCH, SparkContext}


object AppSingleLook extends Application{

  def main(args: Array[String]): Unit = {
    start("spark"){
      val sc = envData.asInstanceOf[SparkContext]
      val rdd2 = sc.textFile("input/user_visit_action.txt")
      val rdd3 = rdd2.map(line => {
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
      }).filter(_.search_keyword== "null")

      // 1,计算所有页面的访问次数

      val rddPage = rdd3.map(bean => (bean.page_id, 1))
        .reduceByKey(_ + _)
      //使用广播变量
      val pageCast = sc.broadcast(rddPage.collect().toMap)
      // 2,计算页面单跳的次数
      val rdd4 = rdd3.map(bean => ((bean.user_id, bean.session_id), (bean.page_id, bean.action_time)))
        .groupByKey()
        .map {
          case ((userId, _), iter) =>
            val list = iter.toList
            // 对时间排序,排完序后取页面id即可
            list.sortBy(_._2).map(_._1)
        }
      // 3,获取每个单跳页面的总次数
      val rdd5 = rdd4.flatMap(list => {
        list.zip(list.tail)
      })
      val rdd6 = rdd5.map(x=>(x,1))
        .reduceByKey(_ + _)
      // 4计算单跳转换率
     val format = new DecimalFormat("0.00%")
      rdd6.map {
        case ((from,to),count)=>
          from+"to:"+to+"=>"+format.format(count.toDouble / pageCast.value.getOrElse(from,1))
      }.saveAsTextFile("output5")

      //rdd7.saveAsTextFile("output5")
    }("output5")
  }

}
