package com.atguigu.application

import com.atguigu.controller.HotCategoryTop10ControllerReview
import summerframework.code.Application

/**
 * 获取热门品类Top10
 */
object HotCategoryTOP10ApplicationReview extends App with Application{

  start("spark"){
    val controllerReview = new HotCategoryTop10ControllerReview
    controllerReview.execute()
  }()

}
