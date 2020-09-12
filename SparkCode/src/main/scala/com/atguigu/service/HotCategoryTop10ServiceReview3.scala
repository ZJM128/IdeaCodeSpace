package com.atguigu.service


import com.atguigu.dao.HotCategoryTop10DAOReview
import com.atguigu.helper.CategoryAccumulator
import summerframework.code.TService
import summerframework.util.EnvUtils


/**
 * 基于HotCategoryTop10ServiceReview2 的问题进行处理
 * 1,思路:reduceByKey的目的是根据品类对点击数,下单数,支付数进行累加 所以可以使用
 *   累加器实现相同的功能
 * 2,解决:创建累加器对每个品类的点击数,下单数,支付数进行分别进行累加
 *    问:累加器如何设计,累加器的输入是什么,累加器的输出是什么
 *    答:
 *    (1)输入参数:根据需要要统计每个品类的点击数,下单数,支付数,所以累加器的输入参数要有每个品类和标识点击数,下单数,支付数的字段
 *       因为个数有限 可以使用元组来存储
 *    (2)输出参数:根据需要要对每个品类的点击数,下单数,支付数进行排序取前十,所以输出参数应包含品类,点击数,下单数,支付数
 *       问:用什么来存储输出参数
 *       答:有两种选择
 *          [1]元组
 *          [2]javaBean
 *       分析:元组:使用场景是个数有限,元组一旦声明元组的个数和数值都是不可变的
 *            javaBean:使用场景多个属性,比较灵活改变Bean中的数据
 *       因为需要分组统计,所以需要判断之前有没有统计过品类
 *       因而需要用可变map进行存储即map(品类,javaBean)
 */
class HotCategoryTop10ServiceReview3 extends TService{

  private val daoReview = new HotCategoryTop10DAOReview()

  override def analysis()= {

    // (1)获取原始数据
    val rdd = daoReview.getUserVisitAction
    // (2) 过滤数据
    val rdd1 = rdd.filter(beans => beans.search_keyword == "null")
    // 创建累加器
    val accumulator = new CategoryAccumulator
    // 注册累加器
    val sparkContext = EnvUtils.getEvn()
    sparkContext.register(accumulator)

    // (3) 分别统计每个指标的数据 使用foreach行动算子,提交job
    rdd1.foreach(beans=>{
      if (beans.click_category_id!="-1" || beans.click_product_id!="-1"){
        accumulator.add(beans.click_category_id,"cc")
      }else if (beans.order_category_ids != "null" || beans.order_product_ids !="null"){
        beans.order_category_ids.split(",").foreach(id=>accumulator.add(id,"oc"))
      }else if (beans.pay_category_ids != "null" || beans.pay_product_ids !="null"){
        beans.pay_category_ids.split(",").foreach(id=>accumulator.add(id,"pc"))
      }
    })
    // (4) 从累加器中获取数据
    val result = accumulator.value
    // (5) 列转换 排序 取前十
    //result.values.toList.sortBy(x=>x).take(10)
    // 问题:为啥 result:map[string,CategoryBean] 使用sortBy(_._2)的时候不能排序
    //  答:不是不能排序是可以排序的 tuples.toMap 转为map后是无序的,要有顺序不能使用map
    result.toList.sortBy(x=>x._2).take(10)



  }

  override def analysis(data: Any): Any = ???
}


