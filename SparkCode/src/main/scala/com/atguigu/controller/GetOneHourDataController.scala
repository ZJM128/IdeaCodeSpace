package com.atguigu.controller

import com.atguigu.service.GetOneHourDataService
import summerframework.code.TController

class GetOneHourDataController extends  TController{
  private val service = new GetOneHourDataService
  override def execute(): Unit = service.analysis()
}
