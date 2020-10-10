package com.atguigu.streaming.app

import com.atguigu.streaming.utils.GetDStreamFromKafkaUtil.getDStreamFromKafka
import org.apache.spark.streaming.Minutes

/**
 * Created by VULCAN on 2020/9/14
 * 需求三：最近一小时广告点击量
 *  广告id:  {时间 -> 点击次数，15:51 ->点击次数   }
 *   1：List [15:50->10,15:51->25,15:52->30]
 *
 *
 *    思路：  窗口的大小： 1h
 *            滑动步长： 随意
 *            采集周期： 窗口的大小 /  采集周期 必须可以整除
 *
 *            封装数据   1600054291787,华北,北京,101,3  =>   ((adsId,hmString),1)  => ((adsId,hmString),N)
 *                        => (adsId , (hmString,N)) => 分组  adsId, { (hmString,N),(hmString,N),(hmString,N)    }
 *                        => 排序，将hmString小的放前面
 */
object Function3AppTest extends BaseApp {

  def main(args: Array[String]): Unit = {
    runAPP{

      // 获取数据
      val kafkaData = getDStreamFromKafka(streamingContext)
      // 封装为bean
      val beanData = getAllBeans(kafkaData)
      // 处理数据
      val result = beanData.window(Minutes(60))
        .map(beans => ((beans.adsId, beans.hmString), 1))
        .reduceByKey(_ + _)
        .map {
          case ((adsId, hmString), count) => (adsId, (hmString, count))
        }.groupByKey()
        .mapValues(iter => iter.toList.sortBy(_._1))
      // 展示
      result.print(1000)

    }
  }
}
