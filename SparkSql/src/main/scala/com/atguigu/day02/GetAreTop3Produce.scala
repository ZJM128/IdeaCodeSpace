package com.atguigu.day02

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, functions}
import org.junit.{After, Test}

/**
 * 思路实现:
 *  ①将需求所需的字段,根据三个表进行join来获取
 *  ②将所有的数据,按照地区和商品名分组,统计每组各有多少条记录count(*)
 *  ③将②统计的数据,根据地区分组 之后按照点击的次数排名 取前三名
 */
class GetAreTop3Produce {

  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("MySpark")
  private val sparkSession: SparkSession = SparkSession.builder()
    .enableHiveSupport()
    .config("spark.sql.warehouse.dir", "hdfs://hadoop102:9820/user/hive/warehouse")
    .config(conf)
    .getOrCreate()

  @Test
  def execTask(): Unit ={

    // 声明UDAF函数
    //val myUDAF = new UserDefinedAggregateFunctionToGetCityInfo
    val getCityInfo = new AggregateFunctionToGetCityInfo
    // 注册UDAF函数
   // sparkSession.udf.register("myUDAF",myUDAF)
   sparkSession.udf.register("myUDAF",functions.udaf(getCityInfo))
    // 使用自定义的UDAF函数
      var sql=
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
          |""".stripMargin

    var sql1=
      """
        |select
        |t1.area,
        |t1.product_name,
        |t1.total,
        |cityInfo,
        |rank()over(partition by t1.area order by t1.total desc) rk
        |from t1
        |
        |""".stripMargin

    var sql2=
      """
        |select
        |t2.area,
        |t2.product_name,
        |t2.total,
        |cityInfo
        |from t2
        |where rk<=3
        |""".stripMargin

    // 切换库
    sparkSession.sql("use myhive")
    // 运行sql 返回df
    val df = sparkSession.sql(sql)
    // 创建临时表 用于后面的sql
    df.createTempView("t1")
    // 运行sql1 返回df
    val df2 = sparkSession.sql(sql1)
    // 创建临时表 用于后面的sql
    df2.createTempView("t2")
    sparkSession.sql(sql2).show(100,truncate = false)

    sparkSession.close()

  }

}
