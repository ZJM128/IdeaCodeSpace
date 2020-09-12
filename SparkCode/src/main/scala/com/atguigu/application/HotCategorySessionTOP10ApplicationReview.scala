package com.atguigu.application


import com.atguigu.controller.HotCategorySessionTOP10ControllerReview
import summerframework.code.Application

/**
 * Top10热门品类中每个品类的Top10活跃点击Session统计
 *    分析:(1)先统计品类在TOP10的品类
 *        (2)过滤出top10的品类的session记录
 *
 */
object HotCategorySessionTOP10ApplicationReview extends App with Application{

  start("spark"){
    val controllerReview = new HotCategorySessionTOP10ControllerReview
    controllerReview.execute()
  }()

}
