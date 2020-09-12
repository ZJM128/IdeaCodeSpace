package com.atguigu.dao

import com.atguigu.bean
import com.atguigu.bean.getCategoryDataToBean
import com.atguigu.util.PropertiesUtil
import org.apache.spark.rdd.RDD
import summerframework.code.TDao

/**
 *
 */
class PageFlowDaoReview extends TDao{
  def getData: RDD[bean.UserVisitAction] ={
    val pathName = PropertiesUtil.getValue("DB.path")
    val rdd = readFile(pathName)
    // 转为Bean对象
     getCategoryDataToBean(rdd)

  }
}
