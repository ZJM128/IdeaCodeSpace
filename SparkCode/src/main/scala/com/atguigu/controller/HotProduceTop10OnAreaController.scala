package com.atguigu.controller

import com.atguigu.service.HotProduceTop10OnAreaService
import summerframework.code.TController

class HotProduceTop10OnAreaController extends TController{
  private val service = new HotProduceTop10OnAreaService
  override def execute(): Unit = {
    val df = service.analysis()
    // 展示数据
    df.show(1000,truncate = false)
  }
}
