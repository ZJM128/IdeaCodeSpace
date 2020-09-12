package com.atguigu.caseCode.application

import com.atguigu.caseCode.bean.UserVisitAction
import com.atguigu.summerframework.code.Application
import org.apache.spark.{SPARK_BRANCH, SparkContext}


object AppHotCategoryAnalysisTop10One extends Application{

  def main(args: Array[String]): Unit = {
    start("spark"){
      val sc = envData.asInstanceOf[SparkContext]
      val rdd = sc.textFile("input/user_visit_action.txt")
      val rdd2 = rdd.map(line => {
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

      // 获取点击数
      val rddClickCategory = rdd2.filter(obj => obj.click_category_id != -1 || obj.click_product_id != -1)
        .groupBy(_.click_category_id)
        .map{
          case (category_id,iter)=>(category_id.toString,iter.size)
        }

      // 获取下单数
      val rddOrderCategory = rdd2.filter(obj => obj.order_category_ids != "null" || obj.order_product_ids != "null")
        .flatMap(_.order_category_ids.split(",").map(category => (category, 1)))
        .reduceByKey(_+_)
      // 获取支付数
      val rddPayCategory = rdd2.filter(obj => obj.pay_category_ids != "null" || obj.pay_product_ids != "null")
        .flatMap(_.pay_category_ids.split(",").map(category => (category, 1)))
        .reduceByKey(_ + _)

      // 合并
      val result = rddClickCategory.leftOuterJoin(rddOrderCategory)
        .leftOuterJoin(rddPayCategory)
        .map {
          case (categoryId, ((cc, oc), pc)) => ((cc, oc.getOrElse(0), pc.getOrElse(0)), categoryId)
        }.sortByKey(ascending = false, 1)
        .take(10)

      sc.makeRDD(result).saveAsTextFile("output")

    }("output")
  }

}
