package com.atguigu.application

import com.atguigu.controller.GetDayAndAreaAndCityFlowController
import summerframework.code.Application

object GetDayAndAreaAndCityFlowApp extends App with Application{
  start("sparkStreaming"){
    val controller = new GetDayAndAreaAndCityFlowController
    controller.execute()
  }()

}
