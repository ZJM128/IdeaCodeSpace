package com.atguigu.application

import com.atguigu.controller.HotProduceTop10OnAreaController
import summerframework.code.Application

object HotProduceTop10OnAreaAppReview extends App with Application{
    start("sparkSql"){
      val controller = new HotProduceTop10OnAreaController
      controller.execute()
    }()
}
