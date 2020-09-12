package com.atguigu.caseCode.application

import com.atguigu.caseCode.bean.UserVisitAction
import com.atguigu.caseCode.helper.CategoryAccumulator
import com.atguigu.summerframework.code.Application
import org.apache.spark.SparkContext


object AppHotCategoryAnalysisTop10Three extends Application{

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

      // 注册累加器
      val accumulator = new CategoryAccumulator
      sc.register(accumulator)

      // 统计  List() 为什么不能用map,因为map是转换算子,没有提交job的能力,spark是懒加载的
     rdd2.foreach(line => {
        if (line.click_category_id != -1 || line.click_product_id != -1) {
          accumulator.add((line.click_category_id.toString, "cc"))
        } else if (line.order_category_ids != "null" || line.order_product_ids != "null") {
          line.order_category_ids.split(",").map(category => accumulator.add((category, "oc")))
        } else if (line.pay_category_ids != "null" || line.pay_product_ids != "null") {
          line.pay_category_ids.split(",").map(category => accumulator.add((category, "pc")))
        } else {
          Nil
        }
      })

      val value = accumulator.value.values.toList
        .sortBy(x => x)
        .take(10)
      sc.makeRDD(value).saveAsTextFile("output2")

    }("output2")
  }

}
