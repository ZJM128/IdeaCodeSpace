package com.atguigu.application

import com.atguigu.controller.GetBlackNameController
import summerframework.code.Application

object GetBlackNameAppReview extends App with Application{

  start("sparkStreaming"){
    val controller = new GetBlackNameController
    controller.execute()
  }()
}
