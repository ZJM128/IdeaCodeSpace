package com.atguigu.dao

import com.atguigu.util.GetDStreamFromKafkaUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.InputDStream
import summerframework.code.TDao
import summerframework.util.EnvUtils

class GetOneHourDataDao extends  TDao{

  // 获取数据
  def getKafkaData: InputDStream[ConsumerRecord[String, String]] ={
    val kafkaData = GetDStreamFromKafkaUtil.getDStreamFromKafka(EnvUtils.getStreamingContext)
    kafkaData
  }

}
