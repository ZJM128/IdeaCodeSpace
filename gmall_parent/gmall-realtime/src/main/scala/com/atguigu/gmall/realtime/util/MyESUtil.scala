package com.atguigu.gmall.realtime.util

import io.searchbox.client.{JestClient, JestClientFactory}
import io.searchbox.client.config.HttpClientConfig

/**
 * 程序中操作ES的工具类
 */
object MyESUtil {

  private var factory:JestClientFactory=_;
  def getClient: JestClient ={
    if (factory==null)builder()
    factory.getObject
  }
  def builder(): Unit ={
    factory=new JestClientFactory
    factory.setHttpClientConfig(
      new HttpClientConfig.Builder("http://hadoop102:9200")
        .multiThreaded(true)
        .maxTotalConnection(20)
        .connTimeout(10000)
        .readTimeout(1000)
        .build()
    )
  }
}
