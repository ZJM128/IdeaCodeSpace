package com.atguigu.controller

import com.atguigu.service.HotCategorySessionTOP10ServiceReview
import summerframework.code.TController

class HotCategorySessionTOP10ControllerReview extends TController{
  val serviceReview = new HotCategorySessionTOP10ServiceReview
  override def execute(): Unit = {
    val result = serviceReview.analysis()
    result.foreach(println)
  }
}
