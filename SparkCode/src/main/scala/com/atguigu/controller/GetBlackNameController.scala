package com.atguigu.controller

import com.atguigu.service.GetBlackNameService
import summerframework.code.TController

class GetBlackNameController extends TController{
  private val service = new GetBlackNameService
  override def execute(): Unit = {
    service.analysis()
  }
}
