package com.atguigu.day02


import java.text.DecimalFormat

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator


/**
 * 自定义强类型的UDAF函数
 *(1)Aggregator需要在继承声明的时候定义输入类型,缓冲区类型,输出类型
 *  所以缓冲区需要使用样例类来存储
 *r
 */
class AggregateFunctionToGetCityInfo extends Aggregator[String,myBuf,String]{
  // 初始化缓冲区
  override def zero: myBuf = myBuf(0,Map[String,Int]())

  // 分区内合并
  override def reduce(b: myBuf, a: String): myBuf = {
    // 统计每个地区的记录数
    b.sum+=1
    // 根据城市 分别统计
    val info = b.cityInfo
    b.cityInfo=info.updated(a,info.getOrElse(a,0)+1)
    b
  }

  // 分区间合并
  override def merge(b1: myBuf, b2: myBuf): myBuf = {
    // 统计每个分区相同地区的总记录数
    b1.sum+=b2.sum
    // 统计每个分区相同城市的总记录数
    val infoMap = b1.cityInfo
    val infoMap2 = b2.cityInfo
    b1.cityInfo = infoMap.foldLeft(infoMap2) {
      case (map, (k, v)) => map.updated(k, map.getOrElse(k, 0) + v)
    }
    b1
  }

  // 返回值
  override def finish(reduction: myBuf): String = {
    // 每个地区的总记录数
    val sum = reduction.sum
    // 取前2的热门城市
    val top2CityInfo = reduction.cityInfo.toList
      .sortBy(_._2)(Ordering.Int.reverse)
      .take(2)
    // 取其他城市 总记录数减去top2城市
    var top2Sum=0
    for ((city,count) <- top2CityInfo) {
      top2Sum+=count
    }
    var otherCitySum=sum-top2Sum
    // 组合其他城市
    val resultInfo = top2CityInfo.+:("其他城市", otherCitySum)
    val result = new StringBuilder
    val format = new DecimalFormat("0.00%")
    resultInfo.foreach{
      case (city,count)=>result.append(city).append(":").append(format.format(count.toDouble/sum)).append(", ")
    }
    result.toString().substring(0,result.length-2)
  }

  //缓冲区编码格式
  override def bufferEncoder: Encoder[myBuf] = Encoders.product

  // 输出参数编码格式
  override def outputEncoder: Encoder[String] = Encoders.STRING
}
 case class myBuf(var sum:Int,var cityInfo: Map[String,Int])