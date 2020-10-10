package com.atguigu.controller

import com.atguigu.service.{GetDayAndAreaAndCityFlowByMysqlService, GetDayAndAreaAndCityFlowService}
import summerframework.code.TController

class GetDayAndAreaAndCityFlowController extends  TController{
  private val service = new GetDayAndAreaAndCityFlowService
  private val service1 = new GetDayAndAreaAndCityFlowByMysqlService
  override def execute(): Unit = service1.analysis()//service.analysis()
}
