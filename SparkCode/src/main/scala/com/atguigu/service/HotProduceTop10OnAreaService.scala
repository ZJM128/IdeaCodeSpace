package com.atguigu.service

import com.atguigu.dao.HotProduceTop10OnAreaDao
import org.apache.spark.sql.DataFrame
import summerframework.code.TService

class HotProduceTop10OnAreaService extends TService{
  private val dao = new HotProduceTop10OnAreaDao
  override def analysis(): DataFrame = {
    val data = dao.getData
    data
  }

  override def analysis(data: Any): Any = ???
}
