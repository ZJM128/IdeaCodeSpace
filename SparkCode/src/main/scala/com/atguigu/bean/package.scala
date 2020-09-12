package com.atguigu

import org.apache.spark.rdd.RDD

// 包中使用
package object bean {

  //用户访问动作表
  case class UserVisitAction(
                              date: String, //用户点击行为的日期
                              user_id: String, //用户的ID
                              session_id: String, //Session的ID
                              page_id: String, //某个页面的ID
                              action_time: String, //动作的时间点
                              search_keyword: String, //用户搜索的关键词
                              click_category_id: String, //某一个商品品类的ID
                              click_product_id: String, //某一个商品的ID
                              order_category_ids: String, //一次订单中所有品类的ID集合
                              order_product_ids: String, //一次订单中所有商品的ID集合
                              pay_category_ids: String, //一次支付中所有品类的ID集合
                              pay_product_ids: String, //一次支付中所有商品的ID集合
                              city_id: String //城市 id
                            )

  // 累加输出javaBean
  case class CategoryBean(
                         var categoryId:String, // 品类
                         var clickNum:Int,// 点击数
                         var orderNum:Int,// 下单数
                         var payNum:Int// 支付数
                         )extends Ordered[CategoryBean] {
    override def compare(that: CategoryBean): Int = {
      var result = -this.clickNum.compareTo(that.clickNum)
      if (result==0){
        result= -this.orderNum.compareTo(that.orderNum)
        if (result==0)
          result= -this.payNum.compareTo(that.payNum)
      }
      result
    }
  }

  /**
   * 读取数据封装成JavaBean工具方法
   * @param rdd
   * @return
   */
  def getCategoryDataToBean(rdd:RDD[String]): RDD[UserVisitAction] ={
    rdd.map(line => {
      val arr = line.split("_")
      UserVisitAction(
        arr(0), //用户点击行为的日期
        arr(1), //用户的ID
        arr(2), //Session的ID
        arr(3), //某个页面的ID
        arr(4), //动作的时间点
        arr(5), //用户搜索的关键词
        arr(6), //某一个商品品类的ID
        arr(7), //某一个商品的ID
        arr(8), //一次订单中所有品类的ID集合
        arr(9), //一次订单中所有商品的ID集合
        arr(10), //一次支付中所有品类的ID集合
        arr(11), //一次支付中所有商品的ID集合
        arr(12) //城市 id
      )
    })
  }


}
