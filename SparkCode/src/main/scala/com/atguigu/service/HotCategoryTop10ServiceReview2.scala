package com.atguigu.service

import com.atguigu.dao.HotCategoryTop10DAOReview
import summerframework.code.TService

/**
 * 基于HotCategoryTop10ServiceReview的性能问题进行优化
 * 思路:
 *   (1)需要的目的是将点击数,下单数,支付数先按品类分组==>(品类,(点击数,下单数,支付数))
 *    [1]问:如何将数据变成(品类,(点击数,下单数,支付数))格式
 *       答:使用map方法将每条数据变成(品类,(点击数,下单数,支付数))格式  符合条件的数据为1
 *           类似mysql的union 即
 *       select sum(第一项),sum(第二项),sum(第三项)
 *       from
 *           (1,0,0)
 *           (0,1,0)
 *           (0,0,1)
 *           (1,0,0)
 *           (0,1,0)
 *           (0,0,1)
 *        group by 每行
 *
 *    [2]使用reduceByKey 进行每个小项的统计
 *   (2)(品类,(点击数,下单数,支付数)) 先按点击数排序,然后下单数排序,最后支付数排序==>元组
 *   (3)取前十
 *
 *
 *   问题:使用了reduceByKey产生了shuffle,性能也会下降
 *   解决:可以使用累加器,处理
 */
class HotCategoryTop10ServiceReview2 extends TService{

  private val daoReview = new HotCategoryTop10DAOReview()

  override def analysis(): Array[((Int, Int, Int), String)] = {
    // 1 获取原始数据
    val rdd = daoReview.getUserVisitAction
    // 2,过滤数 除去搜索的数据
    val rdd1 = rdd.filter(beans => beans.search_keyword == "null")
    // 3,根据条件来 列裁剪
    val rdd2 = rdd1.flatMap(beans => {
      if (beans.click_category_id != "-1" || beans.click_product_id != "-1") {
        List((beans.click_category_id, (1, 0, 0))) // 因为flatMap 需要压平 所以要转为集合
      } else if (beans.order_category_ids != "null" || beans.order_product_ids != "null") {
        beans.order_category_ids.split(",").map(id => (id, (0, 1, 0)))
      } else if (beans.pay_category_ids != "null" || beans.pay_product_ids != "null") {
        beans.pay_category_ids.split(",").map(id => (id, (0, 0, 1)))
      } else {
        Nil
      }
    })
    // 4,统计按品类 统计点击数,下单数,支付数  利用模式匹配进行每小项的累加
    val rdd3 = rdd2.reduceByKey {
      case ((cc, oc, pc), (cc1, oc1, pc1)) => (cc + cc1, oc + oc1, pc + pc1)
    }
    // 5 格式的转换,因为需要对key排序
    val rdd4 = rdd3.map {
      case (categoryId, (cc, oc, pc)) => ((cc, oc, pc), categoryId)
    }
    // 6 降序排序 取前十
    val result = rdd4.sortByKey(ascending = false, 2)
      .take(10)
    result
  }

  override def analysis(data: Any): Any = ???
}
