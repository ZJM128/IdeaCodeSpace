package com.atguigu.service

import com.atguigu.dao.HotCategoryTop10DAOReview
import summerframework.code.TService

/**
 * 处理数据 返回给Controller层
 *
 *求:Top10热门品类
 *  问题:使用了leftOuterJoin 产生了shuffle减低的性能,可优化
 *
 */
class HotCategoryTop10ServiceReview  extends TService{


  private val daoReview = new HotCategoryTop10DAOReview()

  override def analysis(): Array[((Int, Int, Int), String)] = {
    // 1获取数据
    val rdd = daoReview.getUserVisitAction
    // 2 过滤数据 过滤出不是搜索的数据
    val rdd2 = rdd.filter(bean => bean.search_keyword == "null")
    // 3 统计出点击数 =>过滤,列裁剪,统计
    val rdd3 = rdd2.filter(bean => bean.click_category_id != "-1" || bean.click_product_id != "-1")
      .map(bean => (bean.click_category_id, 1))
      .reduceByKey(_ + _)
    // 4 统计出下单数 =>过滤,列裁剪,统计
    val rdd4 = rdd2.filter(beans => beans.order_category_ids != "null" || beans.order_product_ids != "null")
      .flatMap(bean => {
        val arr = bean.order_category_ids.split(",")
        arr.map(categoryId => (categoryId, 1))
      }).reduceByKey(_+_)
    // 5 统计支付数 =>过滤,列裁剪,统计
    val rdd5 = rdd2.filter(beans => beans.pay_category_ids != "null" || beans.pay_product_ids != "null")
      .flatMap(beans => {
        beans.pay_category_ids.split(",")
          .map(categoryId => (categoryId, 1))
      }).reduceByKey(_ + _)
    // 6 合并=>(categoryId,(点击数,下单数,支付数))
    val rdd6 = rdd3.leftOuterJoin(rdd4).leftOuterJoin(rdd5)

    // 7 格式处理 因为需要排序,sortByKey 所以把需要排序的字段作为key
    val rdd7 = rdd6.map {
      case (categoryId, ((cc, oc), pc)) => ((cc, oc.getOrElse(0), pc.getOrElse(0)),categoryId)
    }
    // 8 排序取前十 默认是升序,需要降序取前十
    val result = rdd7.sortByKey(ascending = false, 1).take(10)
    result
  }

  override def analysis(data: Any): Any = ???
}
