package com.atguigu.application

import com.atguigu.controller.{GetBlackNameController, GetOneHourDataController}
import summerframework.code.Application

object GetOneHourDataAppReview extends App with Application{

  start("sparkStreaming"){
    val controller = new GetOneHourDataController
    controller.execute()
  }()
}
