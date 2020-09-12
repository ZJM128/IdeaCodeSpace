package com.atguigu.service

import com.atguigu.dao.HotCategorySessionTOP10DaoReview
import summerframework.code.TService
import summerframework.util.EnvUtils
/**
 * 1,Top10热门品类中每个品类的Top10活跃点击Session统计
 *    分析:(1)先统计品类在TOP10的品类
 *        (2)过滤出top10的品类的session记录
 *
 * 2,优化点:由于top10数据是同享的,每个task都需要获取到,所以可以把top10数据使用广播变量
 *
 */
class HotCategorySessionTOP10ServiceReview extends TService{

  private val daoReview = new HotCategorySessionTOP10DaoReview
  override def analysis(): Array[(String, List[(String, Int)])] = {
    // (1)获取原始数据
    val rdd = daoReview.getTop10Data
    // (2)处理数据
    val rdd2 = rdd.map(line => {
      val word = line.split(",")(3)
      word.substring(0, word.length - 1)
    })
    // (3)获取到了Top的热门的品类
    val sparkContext = EnvUtils.getEvn()
    // 为了方法后续的使用要rdd2转为集合
    val arr = rdd2.collect()
    val broadcastTop10 = sparkContext.broadcast(arr)
    // (4) 获取全部原始数据
    val rdd3 = daoReview.getUserVisitAction
    // (5) 过滤数据
    val rdd4 = rdd3.filter(beans => beans.search_keyword == "null")
    // (6) 过滤出top10的session记录 点击id是否在Top10的数据中
    val rdd5 = rdd4.filter(beans => {broadcastTop10.value.contains(beans.click_category_id)})
    // (7) 列裁剪 ==>((品类,sessionId),1)
    val rdd6 = rdd5.map(beans => ((beans.click_category_id, beans.session_id), 1))
    // (8)按品类和sessionId统计
    val rdd7 = rdd6.reduceByKey(_+_)
    // (9) 格式转换=>(品类,(sessionId,count))
    val rdd8 = rdd7.map {
      case ((categoryId, sessionId), count) => (categoryId, (sessionId, count))
    }
    // (10) 按品类分组
    val rdd9 = rdd8.groupByKey()
    // (11) 排序 取每个品类的session的top10
    val rdd10 = rdd9.mapValues(iter => iter.toList
      .sortBy(_._2)(Ordering.Int.reverse)
      .take(10))
    // (12)获取结果
    val array = rdd10.collect()
    array

  }

  override def analysis(data: Any): Any = ???
}
