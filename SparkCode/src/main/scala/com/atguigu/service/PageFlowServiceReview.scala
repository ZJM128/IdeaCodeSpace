package com.atguigu.service

import java.text.DecimalFormat

import com.atguigu.dao.PageFlowDaoReview
import org.apache.spark.rdd.RDD
import summerframework.code.TService
import summerframework.util.EnvUtils
/**
 * 页面单跳转换率统计
 * 分析:
 *  (1)问:什么是页面的单跳转
 *     答:就是两个页面之间的跳转,比如A=>B B=>C A=>C 但是 A=>B=>C 此时A到C不是单跳转
 *  (2)问:转换率怎么算
 *     答:每个页面到另一个页面的单跳转次数/每个页面访问的总数
 * 思路:
 *    (1)某个用户 某次session 按时间顺序 进行页面的点击
 *    (2)所以按用户和session分组得到==>((用户,session),iter((点击时间,页面id),(点击时间1,页面id1),....))
 *    (3)根据点击时间按升序排序==>iter((点击时间,页面id),(点击时间1,页面id1),....)
 *    (4)通过map转换得到iter(页面,页面1.....)
 *    (5)通过zip(iter.tail)得到==>iter((页面,页面1),(页面,页面2),......)
 *    (6)转换格式==>((页面,页面1),1),((页面,页面2),1),......
 *    (7)根据key进行统计==>iter(((页面,页面1),count),((页面,页面2),count1),......)
 *    (8)计算转换率
 *
 *
 */

class PageFlowServiceReview extends TService{
  private val daoReview = new PageFlowDaoReview
  override def analysis(): Array[(String, String)] = {
    // (1)获取数据
    val rdd = daoReview.getData
    // (2)统计页面的总点击数
    val rdd2 = rdd.map(beans => (beans.page_id, 1))
      .reduceByKey(_ + _)
    //  因为这个变量是每个task都需要用到的 所以使用广播变量
    val sparkContext = EnvUtils.getEvn()
    val broadcastValue = sparkContext.broadcast(rdd2.collect().toMap)
   // broadcastValue.value.foreach(println)
    // (3) 列转换=>(((用户id,sessionId),(点击时间,页面)),((用户id1,sessionId1),(点击时间1,页面1)),....)
    val rdd3 = rdd.map(beans => ((beans.user_id, beans.session_id), (beans.action_time, beans.page_id)))
    // (4)按用户和session分组得到==>((用户,session),iter((点击时间,页面id),(点击时间1,页面id1),....))
    val rdd4 = rdd3.groupByKey()
    // (5)根据点击时间按升序排序==>list((点击时间,页面id),(点击时间1,页面id1),....)
    val rdd5 = rdd4.map {
      case ((user_id, session_id), iter) => iter.toList.sortBy(_._1)
    }
    // (6)通过map转换得到iter(页面,页面1.....)
    val ree6 = rdd5.map(iter => iter.map {
      case (iter, pageId) => pageId
    })
    // (7)通过zip(iter.tail)得到==>iter((页面,页面1),(页面,页面2),......)
    val rdd7 = ree6.map(iter => iter.zip(iter.tail))
    // (8)转换格式==>iter(((页面,页面1),1),((页面,页面2),1),......)
    val rdd8 = rdd7.flatMap(iter => iter.map(x => (x, 1)))
    // (9)根据key进行统计==>((页面,页面1),count),((页面,页面2),count1),......
    val rdd9 = rdd8.reduceByKey(_ + _)
    // (10)计算转换率

    // 数据格式化 格式一定要正确 不然会出现问题
    val format = new DecimalFormat("0.00%")
    val result = rdd9.map {
      case ((from, to), count) => (from+" to "+to, format.format(count.toDouble / broadcastValue.value.getOrElse(from, 1)))
    }
    result.collect()

  }

  override def analysis(data: Any): Any = ???
}
