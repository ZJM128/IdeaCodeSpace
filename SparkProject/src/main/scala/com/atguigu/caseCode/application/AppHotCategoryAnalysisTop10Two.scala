package com.atguigu.caseCode.application

import com.atguigu.caseCode.bean.UserVisitAction
import com.atguigu.summerframework.code.Application
import org.apache.spark.SparkContext


object AppHotCategoryAnalysisTop10Two extends Application{

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

      // 使用类似 union的方法
      val rdd3 = rdd2.flatMap(ojb => {
        if (ojb.click_category_id != -1 || ojb.click_product_id != -1) {
          List((ojb.click_category_id.toString, (1, 0, 0)))
        } else if (ojb.order_category_ids != "null" || ojb.order_product_ids != "null") {
          ojb.order_category_ids.split(",").map(category => (category, (0, 1, 0)))
        } else {
          ojb.pay_category_ids.split(",").map(category => (category, (0, 0, 1)))
        }
      })

      // 统计每个品类category_ids 的点击数,订单数,下单数
      val rdd4 = rdd3.reduceByKey { case ((cc, oc, pc), (cc1, oc1, pc1)) => (cc + cc1, oc + oc1, pc + pc1) }
      // 排序
      val array = rdd4.map {
        case (categoryID, (cc, oc, pc)) => ((cc, oc, pc), categoryID)
      }.sortByKey(ascending = false,1)
        .take(10)
      sc.makeRDD(array).saveAsTextFile("output1")

    }("output1")
  }

}
