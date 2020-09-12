package com.atguigu.controller

import com.atguigu.service.{HotCategoryTop10ServiceReview, HotCategoryTop10ServiceReview2, HotCategoryTop10ServiceReview3}
import summerframework.code.TController

/**
 * 对数据再次加工 交给前端展示
 */
class HotCategoryTop10ControllerReview extends TController{
  private val serviceReview = new HotCategoryTop10ServiceReview
  val serviceReview2 = new HotCategoryTop10ServiceReview2
  val serviceReview3 = new HotCategoryTop10ServiceReview3
  override def execute(): Unit = {
    println("方法一")
    val result = serviceReview.analysis()
    result.foreach(println)
    println("方法二")
    val result2 = serviceReview2.analysis()
    result2.foreach(println)
    println("方法三")
    val result3 = serviceReview3.analysis()
    result3.foreach(println)
  }
}
