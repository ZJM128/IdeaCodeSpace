package com.atguigu.dao

import com.atguigu.util.GetDStreamFromKafkaUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.InputDStream
import summerframework.code.TDao
import summerframework.util.EnvUtils

class GetDayAndAreaAndCityFlowDao extends TDao{

  def getDayAndAreaAndCityFlowData: InputDStream[ConsumerRecord[String, String]] ={
    GetDStreamFromKafkaUtil.getDStreamFromKafka(EnvUtils.getStreamingContext)
  }
}
