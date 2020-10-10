package com.atguigu.gmall.realtime.util

import redis.clients.jedis.Jedis

object RedisUtil {
  val host = ConfigUtil.getProperty("redis.host")
  val port = ConfigUtil.getProperty("redis.port").toInt

  def getClient = {
    new Jedis(host, port)
  }


}
