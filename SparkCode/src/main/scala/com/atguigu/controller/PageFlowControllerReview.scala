package com.atguigu.controller

import com.atguigu.service.PageFlowServiceReview
import summerframework.code.TController

class PageFlowControllerReview extends TController{
  private val serviceReview = new PageFlowServiceReview
  override def execute(): Unit = {
    val result = serviceReview.analysis()
    result.foreach(println)
  }
}
