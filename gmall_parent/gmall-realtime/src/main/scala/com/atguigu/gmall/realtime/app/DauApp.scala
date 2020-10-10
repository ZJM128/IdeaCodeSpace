package com.atguigu.gmall.realtime.app

import com.atguigu.gmall.realtime.bean.StartupLog
import com.atguigu.gmall.realtime.util.{MyKafkaUtil, RedisUtil}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Durations.seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.json4s.JsonAST.JObject
import org.json4s.jackson.JsonMethods

/**
 * 从kafka获取启动日志流
 */
object DauApp {

  def main(args: Array[String]): Unit = {
    // 1 创建一个StreamingContext
    val conf = new SparkConf().setMaster("local[2]").setAppName("DauApp")
    val ssc = new StreamingContext(conf, seconds(3))
    // 2 获取启动日志流
    val sourceStream = MyKafkaUtil.getKafkaStream(ssc, "gmall_startup_topic")
    sourceStream.print(1000)
    ssc.start()
    ssc.awaitTermination()
  }

  private def parseToStartupLog(sourceStream:DStream[String]) ={
    sourceStream.map(jsonString=>{
      val json = JsonMethods.parse(jsonString)
      var jerseyCommon=json\"common"
      var jTs=json\"ts"
      implicit val d = org.json4s.DefaultFormats
      jerseyCommon.merge(JObject("ts"->jTs)).extract[StartupLog]

    })
  }

  def distinct(startupLogStream:DStream[StartupLog])={
    var preKey="mids"
    startupLogStream.mapPartitions(startupLogIt=>{
      val client = RedisUtil.getClient
      val result = startupLogIt.filter(startupLog => {
        val key = preKey + startupLog.logDate
        val result = client.sadd(key, startupLog.mid)
        result == 1
      })
      client.close()
      result
    })
  }

}
