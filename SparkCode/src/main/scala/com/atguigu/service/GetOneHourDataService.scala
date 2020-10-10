package com.atguigu.service

import com.atguigu.dao.GetOneHourDataDao
import com.atguigu.util.DataToBeanUtil
import org.apache.spark.streaming.Minutes
import summerframework.code.TService

/**
 * 需求三：最近一小时广告点击量
 *   广告id:  {时间 -> 点击次数，15:51 ->点击次数   }
 *   1：List [15:50->10,15:51->25,15:52->30]
 *  思路:
 *  (1)某个广告,近一个小时的广告的点击量==>近一个小时是不断变化的 要求窗口的大小为一个小时
 *  (2)窗口大小为1小时,步长随意
 *  (3)按广告和时间进行分组 统计每个广告在某个时间的的总和
 *  (4)转换格式 以每个广告分组
 *  (5)按照时间排序 获取每个广告在某个时间的点击量
 */
class GetOneHourDataService extends TService{
  private val dao = new GetOneHourDataDao
  override def analysis(): Any = {
    // 获取kafka的数据
    val kafkaData = dao.getKafkaData
    // 封装成bean
    val beanValue = DataToBeanUtil.getAllBeans(kafkaData)
    // 求的是某个广告 近一个小时的广告的点击量==>近一个小时是不断变化的 要求窗口的大小为一个小时
    beanValue.window(Minutes(60))
      .map(beans=>((beans.adsId,beans.hmString),1))
      .reduceByKey(_+_)
      .map{case ((adsId,hmString),count)=>(adsId,(hmString,count))}
      .groupByKey()
      .mapValues(iter=>iter.toList.sortBy(_._2))
      .print(1000)

  }

  override def analysis(data: Any): Any = ???
}
