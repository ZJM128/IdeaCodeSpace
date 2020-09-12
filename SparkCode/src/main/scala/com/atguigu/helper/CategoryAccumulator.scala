package com.atguigu.helper

import com.atguigu.bean.CategoryBean
import org.apache.spark.util.AccumulatorV2
import scala.collection.mutable

/**
 * 累加器
 * 问:累加器如何设计,累加器的输入是什么,累加器的输出是什么
 *   答:
 *   (1)输入参数:根据需要要统计每个品类的点击数,下单数,支付数,所以累加器的输入参数要有每个品类和标识点击数,下单数,支付数的字段
 *      因为个数有限 可以使用元组来存储
 *   (2)输出参数:根据需要要对每个品类的点击数,下单数,支付数进行排序取前十,所以输出参数应包含品类,点击数,下单数,支付数
 *      问:用什么来存储输出参数
 *      答:有两种选择
 *         [1]元组
 *         [2]javaBean
 *      分析:元组:使用场景是个数有限,元组一旦声明元组的个数和数值都是不可变的
 *           javaBean:使用场景多个属性,比较灵活改变Bean中的数据
 *      因为需要分组统计,所以需要判断之前有没有统计过品类
 *      因而需要用可变map进行存储即map(品类,javaBean)
 *
 */
class CategoryAccumulator extends AccumulatorV2[(String, String), mutable.Map[String,CategoryBean]] {

  private var result = mutable.Map[String, CategoryBean]()
  // 判断是否为空
  override def isZero: Boolean = result.isEmpty
  // 复制累加器到每个executor的task中
  override def copy(): AccumulatorV2[(String, String), mutable.Map[String,CategoryBean]] = new CategoryAccumulator
  // 先清空task累加器,便于之后的操作不出错
  override def reset(): Unit = result.clear()
  // 分区内累加(task中)
  override def add(v: (String, String)): Unit = {
    if (v._2=="cc"){
      val bean = result.getOrElse(v._1, CategoryBean(v._1, 0, 0, 0))
      bean.clickNum+=1
      result.put(v._1,bean)
    }else if (v._2=="oc"){
      val bean = result.getOrElse(v._1, CategoryBean(v._1, 0, 0, 0))
      bean.orderNum+=1
      result.put(v._1,bean)
    }else{
      val bean = result.getOrElse(v._1, CategoryBean(v._1, 0, 0, 0))
      bean.payNum+=1
      result.put(v._1,bean)
    }
  }
  // 分区间合并 task对逻辑进行运算完后汇总到Driver端进行最后的合并
  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String,CategoryBean]]): Unit = {
    val temp = other.value
    for ((k,v)<-temp){
      val bean = result.getOrElse(k, CategoryBean(k, 0, 0, 0))
      bean.clickNum+=v.clickNum
      bean.orderNum+=v.orderNum
      bean.payNum+=v.payNum
      result.put(k,bean)
    }
  }
  // 返回结果
  override def value: mutable.Map[String,CategoryBean] = result
}
