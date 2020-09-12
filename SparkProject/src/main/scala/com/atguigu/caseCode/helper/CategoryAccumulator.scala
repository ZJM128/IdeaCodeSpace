package com.atguigu.caseCode.helper

import com.atguigu.caseCode.bean.CategoryInfo
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * 累加器
 * (1)要考虑,这个累加器做什么的
 *        统计每个品类的点击量,下单数,支付数
 * (2)考虑🤔这个累加器的输入参数类型和输出参数的类型
 *          输入参数:因为要考虑,那种类型(点击量,下单数,支付数)的那个品类的统计数,所以采用元组的个数进行传参(品类,种类)
 *          输出参数:需要输出,那个品类的点击量,下单数,支付数等信息 所有需要封装成javaBean
 *                   问题:为什么不是元组,而是javaBean 因为需要存储的点击量,下单量,支付数是动态变化的,而
 *                   元组声明了就不可以改变了,所以使用javaBean比较好
 */
class CategoryAccumulator extends AccumulatorV2[(String, String),mutable.Map[String,CategoryInfo]]{

  private var result: mutable.Map[String, CategoryInfo] = mutable.Map[String, CategoryInfo]()

  override def isZero: Boolean = result.isEmpty

  override def copy(): AccumulatorV2[(String, String), mutable.Map[String, CategoryInfo]] = new CategoryAccumulator

  override def reset(): Unit = result.clear()

  override def add(v: (String, String)): Unit = {
      if(v._2=="cc"){
        val info = result.getOrElse(v._1, CategoryInfo(v._1, 0, 0, 0))
        info.cc+=1
        result.put(v._1,info)
      }else if (v._2=="oc"){
        val info = result.getOrElse(v._1, CategoryInfo(v._1, 0, 0, 0))
        info.oc+=1
        result.put(v._1,info)
      }else{
        val info = result.getOrElse(v._1, CategoryInfo(v._1, 0, 0, 0))
        info.pc+=1
        result.put(v._1,info)
      }
  }
  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, CategoryInfo]]): Unit = {
    val value1 = other.value
    for ((key,v)<-value1){
      val categoryInfo = result.getOrElse(key,CategoryInfo(key, 0, 0, 0))
      categoryInfo.cc+=v.cc
      categoryInfo.pc+=v.pc
      categoryInfo.oc+=v.oc
      result.put(key,categoryInfo)
    }

  }
  override def value: mutable.Map[String, CategoryInfo] = result
}
