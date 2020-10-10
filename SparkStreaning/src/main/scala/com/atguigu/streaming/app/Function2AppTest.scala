package com.atguigu.streaming.app

import com.atguigu.streaming.utils.{GetDStreamFromKafkaUtil, JDBCUtil}

/**
 * 实时统计每天各地区各城市各广告的点击总流量，并将其存入MySQL\
 *  日期 地区 城市 广告 为联合主键
 *  思路:因为是每天的的数据 采集周期为3秒 所以需要把当前时间的数据加上之前的数据
 *      需要使用有状态的DS updateStateByKey
 *
 *
 *
 * 实时统计每天各地区各城市各广告的点击总流量，并将其存入MySQL
 *
 *    在Mysql中建表存储计算的结果： 日期，地区，城市，广告，点击量
 *                                日期，地区，城市，广告 作为联合主键
 *
 *     思路：   总流量。 有状态的计算！ 使用updateStageByKey算子！
 *              统计的数据的时间范围：   0:00 ----> 当前
 *                          统计时间：  15：00
 *                                      在15：00统计时，将今天 各地区各城市各广告的点击总流量都进行统计
 *
 *
 *              如何将统计的结果存入Mysql?
 *                    insert: 主键冲突?
 *                        sqoop:   updateMode=  默认updateOnly(翻译为 update )
 *                                      |  allowInsert（翻译为 insert into xxx ON DUPLICATE KEY）
 *
 * INSERT INTO  `area_city_ad_count` VALUES('2020-09-14','华北','北京','101',3)
 * ON DUPLICATE KEY UPDATE COUNT=VALUES(COUNT)
 *                    update?
 *
 *
 * 问题： 使用ck目录保存每个采集周期计算的结果的状态！会造成小文件！
 *            解决：  ① 在集群中部署，ck目录在hdfs。 可以先一个程序，程序监控ck目录，将已经过期的ck目录删除！
 *                          程序-----> 每间隔5分钟提交一个Job
 *                          监控程序----->删除当前时间前15分钟之前所有生成的ck目录
 *                   ② 从根本上解决，不使用ck目录记录state，自然不能使用updateStageByKey算子
 *                        怎么完成有状态的计算？  自己维护state(mysql,redis)!
 *                              a) 程序在计算当前 这批数据时，先从数据库中，查询state
 *                                    state + 当前计算的结果  合并 =  新的state
 *                              b) 将state写入数据库
 *
 *
 *
 *
*/

object Function2AppTest extends BaseApp {
  def main(args: Array[String]): Unit = {
    runAPP {

      streamingContext.checkpoint("Function2AppTest")
      // 1,从kafka中获取数据
      val kafkaData = GetDStreamFromKafkaUtil.getDStreamFromKafka(streamingContext)
      //  2,把数据转为bean
      val dataDS = getAllBeans(kafkaData)
      // 3,把数据转为((日期,地区,城市),1)
      val dataDSMap = dataDS.map(beans => ((beans.dayString, beans.area, beans.city,beans.adsId), 1))
      // 4,当前时间和之前时间进行统计合并=>((日期,地区,城市),sum)
      val result = dataDSMap.updateStateByKey((values: Seq[Int], v: Option[Int]) => Some(values.sum + v.getOrElse(0)))
      // 5,并将其存入MySQL
      result.foreachRDD(rdd=>{
        // 对每个分区的数据进行插入数据库的操作
          rdd.foreachPartition(iter=>{
            // 把数据库的连接信息写在这里 也就是每个分区创建一个,避免了每条记录都创建一次数据库连接
            val connection = JDBCUtil.getConnection()
            val statement = connection.prepareStatement(
              """
                |INSERT INTO  `area_city_ad_count` VALUES(?,?,?,?,?)
                |ON DUPLICATE KEY UPDATE COUNT=?
                |""".stripMargin)
            iter.foreach{
              case((date,area,city,adsId),sum)=>
                statement.setString(1,date)
                statement.setString(2,area)
                statement.setString(3,city)
                statement.setString(4,adsId)
                statement.setInt(5,sum)
                statement.setInt(6,sum)
                // 执行更新插入
                statement.executeUpdate()
            }

            // 关闭资源
            statement.close()
            connection.close()
          })
        }
      )

    }
  }


}
