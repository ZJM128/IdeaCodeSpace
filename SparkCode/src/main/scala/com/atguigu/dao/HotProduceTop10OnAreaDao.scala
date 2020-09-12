package com.atguigu.dao

import com.atguigu.helper.Top2CityAggregator
import org.apache.spark.sql.{DataFrame, functions}
import summerframework.code.TDao
import summerframework.util.EnvUtils

class HotProduceTop10OnAreaDao extends TDao{

  def getData: DataFrame ={
    // 获取sparkSession
    val session = EnvUtils.getSparkSession()
    // 导入隐式类
    import session.implicits._
    // 创建UDAF函数
    val aggregator = new Top2CityAggregator
    // 注册
    session.udf.register("myUDAF",functions.udaf(aggregator))
   // 切换库
    session.sql("use myhive")
    val df = session.sql(
      """
        |select
        |ci.area ,
        |pi.product_name,
        |count(*) total,
        |myUDAF(city_name) cityInfo
        |from user_visit_action uva left join city_info ci
        |on uva.city_id = ci.city_id
        |left join product_info pi
        |on uva.click_product_id=pi.product_id
        |where pi.product_id is not null
        |group by ci.area,pi.product_id,pi.product_name
        |""".stripMargin)
    // 创建临时表  用于后面的sql的使用
    df.createTempView("t1")
    val df1 = session.sql(
      """
        |select
        |t1.area,
        |t1.product_name,
        |t1.total,
        |t1.cityInfo,
        |rank()over(partition by t1.area order by t1.total) rk
        |from t1
        |""".stripMargin)
    // 创建临时表  用于后面的sql的使用
    df1.createTempView("t2")
    val df2= session.sql(
      """select
        |t2.area,
        |t2.product_name,
        |t2.total,
        |t2.cityInfo
        |from t2
        |where t2.rk<=3
        |""".stripMargin)
    // 获取数据返回数据集
    df2

  }
}
