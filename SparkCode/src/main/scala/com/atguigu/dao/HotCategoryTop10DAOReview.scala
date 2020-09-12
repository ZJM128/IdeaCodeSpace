package com.atguigu.dao

import com.atguigu.bean.{UserVisitAction, getCategoryDataToBean}
import com.atguigu.util.PropertiesUtil
import org.apache.spark.rdd.RDD
import summerframework.code.TDao

/**
 * 连数据库 获取数据集 交给service端处理
 */
class HotCategoryTop10DAOReview extends TDao{

  // 从文件中获取原始数据 返回给service端
  def getUserVisitAction:RDD[UserVisitAction]={
    // 获取文件路径
    val pathName = PropertiesUtil.getValue("DB.path")
    val rdd = readFile(pathName)
    /*val rdd2 = rdd.map(line => {
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
    rdd2*/
    getCategoryDataToBean(rdd)
  }

}
