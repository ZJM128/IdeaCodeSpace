package com.atguigu.helper

import java.text.DecimalFormat

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator

/**
 * 计算每个地区中top2城市的百分比和剩余城市的占的百分比
 * 由于是强类型的UDAF函数 所以需要使用样例类来存储缓冲区的数据
 * (1)输入参数:每个城市 :string
 * (2)缓冲区:每个区的记录数:int,每个城市的记录数:Map[城市名,记录数]
 * (3)输出:string 城市名:百分比  其他城市:百分比
 *
 */
class Top2CityAggregator extends Aggregator[String,myBuf,String]{
  // 清除缓冲区
  override def zero: myBuf = myBuf(0,Map[String,Int]())

  // 合并缓冲区内的数据(分区内)
  override def reduce(b: myBuf, a: String): myBuf = {
    // 累计每个地区的总记录数
    b.sum+=1
    // 累计每个城市出现的记录数
    val cityInfo = b.cityInfo
    b.cityInfo=cityInfo.updated(a,cityInfo.getOrElse(a,0)+1)
    // 返回总计后的结果
    b
  }
 // 合并缓冲区间的数据(分区间)
  override def merge(b1: myBuf, b2: myBuf): myBuf = {
    // 合计分区间每个地区的总记录数
    b1.sum+=b2.sum
    // 合并分区间每个城市的记录数
    val cityInfo1 = b1.cityInfo
    val cityInfo2 = b2.cityInfo
    b1.cityInfo=cityInfo1.foldLeft(cityInfo2){
      case (map,(k,v))=>map.updated(k,map.getOrElse(k,0)+v)
    }
    b1
  }

  // 把终最终的数据返回
  override def finish(reduction: myBuf): String = {
    // 取的每个地区的总记录数
    val sum = reduction.sum
    // 计算top2城市
    val top2City = reduction.cityInfo.toList.sortBy(_._2)(Ordering.Int.reverse).take(2)
    // 计算其他的城市的记录数
    var top2Sum=0
    for ((k,v)<-top2City){
      top2Sum+=v
    }
    var otherCitySum=sum-top2Sum
    // 把其他的城市合并到top2中
    val result = top2City.:+("其他城市", otherCitySum)
    // 处理格式
    val format = new DecimalFormat("0.00%")
    val builder = new StringBuilder
    result.foreach{
      case (city,count)=>builder.append(city).append(":").append(format.format(count.toDouble/sum)).append(", ")
    }
    builder.toString().substring(0,builder.length-2)
  }

  // 指定缓冲区的编码格式 固定写法
  override def bufferEncoder: Encoder[myBuf] = Encoders.product

  // 指定输出的编码格式
  override def outputEncoder: Encoder[String] = Encoders.STRING
}

case class myBuf(var sum:Int,var cityInfo:Map[String,Int])
