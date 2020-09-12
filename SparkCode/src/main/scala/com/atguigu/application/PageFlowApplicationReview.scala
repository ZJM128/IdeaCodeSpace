package com.atguigu.application

import com.atguigu.controller.PageFlowControllerReview
import summerframework.code.Application

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
 *    (6)转换格式==>iter(((页面,页面1),1),((页面,页面2),1),......)
 *    (7)根据key进行统计==>iter(((页面,页面1),count),((页面,页面2),count1),......)
 *    (8)计算转换率
 *
 *
 */
object PageFlowApplicationReview extends App with Application{
  start("spark"){
    val controllerReview = new PageFlowControllerReview
    controllerReview.execute()
  }()

}
