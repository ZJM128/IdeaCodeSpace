package com.atguigu.day02

import java.text.DecimalFormat

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, HIVE_TYPE_STRING, IntegerType, MapType, StringType, StructField, StructType}

/**
 * spark 2.0
 * 定义:弱类型的DUAF
 * 需求:北京21.2%，天津13.2%，其他65.6%
 * 分析:需要统计每个地区的前二的热门城市的占有百分比,和剩余的总共百分比,需要在对分组后进行该函数的调用
 *      输入参数:每个城市 类型为String
 *      缓冲区的参数:每个地区的总记录 类型为string 每个城市出现的次数=>需要一个Map[城市,次数]
 *      输出的参数:北京21.2%，天津13.2%，其他65.6%  类型为string
 * 思路:统计每个地区总的出现的记录 和每个城市出现的次数
 *
 *
 *
 *
 */
class UserDefinedAggregateFunctionToGetCityInfo extends UserDefinedAggregateFunction{
  // 输入参数类型
  override def inputSchema: StructType = new StructType(Array(StructField("city_name",StringType)))

  // 缓冲区参数类型
  override def bufferSchema: StructType = new StructType(Array(StructField("sum",IntegerType),StructField("cityInfo",MapType(StringType,IntegerType))))

  // 输出参类型
  override def dataType: DataType = StringType

  // 是否是严格模式 默认为true
  override def deterministic: Boolean = true

  // 初始化缓冲区
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0)=0
    buffer(1)=Map[String,Int]()
  }

  // 缓冲区内的计算逻辑
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    // 每个地区统计总记录 问:为什么是buffer.getInt(0) 答:因为定义的类型为IntegerType类型 所以需要getInt来转换成int类型
      buffer(0)=buffer.getInt(0)+1
    // 计算每个城市的总记录
    val cityInfoMap = buffer.getMap[String, Int](1)
    val city = input.getString(0)
    val result = cityInfoMap.updated(city, cityInfoMap.getOrElse(city, 0) + 1)
    buffer(1)=result
  }

  // 缓冲区间的计算逻辑
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    // 统计每个分区间的相同地区的总记录
    buffer1(0)=buffer1.getInt(0)+buffer2.getInt(0)
    val cityInfoMap1 = buffer1.getMap[String, Int](1)
    val cityInfoMap2 = buffer2.getMap[String, Int](1)
    // 将其中的一个map中的所有的k-v对合并到另一个Map中
    val resultMap = cityInfoMap2.foldLeft(cityInfoMap1) {
      case (map, (k, v)) => map.updated(k, map.getOrElse(k, 0) + v)
    }
    buffer1(1)=resultMap
  }

  // 返回值==>输出的参数:北京21.2%，天津13.2%，其他65.6%
  override def evaluate(buffer: Row): Any = {
    val format = new DecimalFormat("0.00%")
    // 获取每个地区的总记录数
    val sum = buffer.getInt(0)
    val cityInfo = buffer.getMap[String, Int](1)
    // 根据记录数尽心排序 取前二
    val listTop2 = cityInfo.toList.sortBy(_._2)(Ordering.Int.reverse).take(2)
    var top2Count:Int=0
    for ((k,v)<-listTop2){
      top2Count+=v
    }
    val otherCity = sum-top2Count
    // 把其他城市的信息加到Top2中
    var resultList=listTop2:+("其他",otherCity)
    val result = new StringBuffer()
    resultList.foreach{
      case (city,count)=>result.append(city).append(":").append(format.format(count.toDouble/sum)).append(", ")
    }
    result.toString.substring(0,result.length()-2)

  }
}
