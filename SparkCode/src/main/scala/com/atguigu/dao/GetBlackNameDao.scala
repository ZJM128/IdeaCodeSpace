package com.atguigu.dao

import com.atguigu.util.{DataToBeanUtil, GetDStreamFromKafkaUtil}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import summerframework.bean.AdsInfo
import summerframework.code.TDao
import summerframework.util.EnvUtils

class GetBlackNameDao extends TDao{

  // 获取数据
  def getKafkaData: InputDStream[ConsumerRecord[String, String]] ={
    val kafkaData = GetDStreamFromKafkaUtil.getDStreamFromKafka(EnvUtils.getStreamingContext)
    kafkaData
  }
}
