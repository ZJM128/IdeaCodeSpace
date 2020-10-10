package com.atguigu.streaming.app

import com.atguigu.streaming.utils.{GetDStreamFromKafkaUtil, JDBCUtil}

import scala.collection.mutable.ListBuffer

/**
 *
 * 实现实时的动态黑名单机制：将每天对某个广告点击超过 100 次的用户拉黑。
 * 注：黑名单保存到MySQL中。
 *
 *    准备工作：  black_list (保存黑名单)
 *
 *    思路：   ①对每批数据，都进行计算，计算当前这批数据，每个用户对每个广告的点击次数
 *                  1600054291787,华北,北京,101,3 =>    ((day,用户id,广告id),1)  => ((day,用户id,广告id),N)
 *
 *             ②计算完每批数据后，需要自己维护状态
 *                  ((用户id,广告id),N) 写入到 mysql user_ad_count
 *                  注意：  将之前的值 和 当前的N 进行 累加，之后再插入到 user_ad_count
 *                INSERT INTO user_ad_count VALUES(?,?,?,?)
 *                ON DUPLICATE KEY UPDATE COUNT=COUNT+?
 *
 *            ③ 从  user_ad_count 查询最新的状态，将查询的结果进行判断
 *                  哪些用户当前最新的状态已经 超过 100
 *                  select userid from user_ad_count where count > 100
 *
 *
 *            ④ 将③查询的用户，插入black_list表中
 *                   INSERT INTO black_list VALUES(?)
 *                    ON DUPLICATE KEY UPDATE userid=?
 */
object Function1AppTest extends BaseApp {

  def main(args: Array[String]): Unit = {
    runAPP{

      // 1,从kafka中获取数据
      val kafkaData = GetDStreamFromKafkaUtil.getDStreamFromKafka(streamingContext)
      //  2,把数据转为bean
      val dataDS = getAllBeans(kafkaData)

      //①对每批数据，都进行计算，计算当前这批数据，每个用户对每个广告的点击次数 统计今天内用户点击的次数
      // 1600054291787,华北,北京,101,3 =>    ((day,用户id,广告id),1)  => ((day,用户id,广告id),N)
      val ds = dataDS.map(beans => ((beans.dayString, beans.userId, beans.adsId), 1))
        .reduceByKey(_ + _)
      // ②计算完每批数据后，需要自己维护状态
      //   ((用户id,广告id),N) 写入到 mysql user_ad_count
      ds.foreachRDD(rdd=>{
        // 以分区一个数据单位
        rdd.foreachPartition(iter=>{
          val connection = JDBCUtil.getConnection()
          val ps1 = connection.prepareStatement(
            """
              |INSERT INTO user_ad_count VALUES(?,?,?,?)
              |ON DUPLICATE KEY UPDATE COUNT=COUNT+?
              |""".stripMargin)
          iter.foreach{
            case ((dayString,userId,adsId),count)=>
              ps1.setString(1,dayString)
              ps1.setString(2,userId)
              ps1.setString(3,adsId)
              ps1.setInt(4,count)
              ps1.setInt(5,count)
              ps1.executeUpdate()
          }
          // 关闭资源
          ps1.close()

          //  ③ 从  user_ad_count 查询最新的状态，将查询的结果进行判断
          val ps2 = connection.prepareStatement(
            """
              |select userid from user_ad_count where count > 20
              |""".stripMargin)
          // 获取结果
          val resultSet = ps2.executeQuery()


          //遍历结果，查询需要拉黑的人
          val needToAddToBL: ListBuffer[String] = ListBuffer[String]()
          while (resultSet.next()){
            needToAddToBL.append(resultSet.getString("userid"))
          }
          resultSet.close()
          ps2.close()
          //   ④ 将③查询的用户，插入black_list表中
          val ps3 = connection.prepareStatement(
            """
              | INSERT INTO black_list VALUES(?)
              | ON DUPLICATE KEY UPDATE userid=?
              |""".stripMargin)

          needToAddToBL.foreach(name=>{
            ps3.setString(1,name)
            ps3.setString(2,name)
            // 执行sql
            ps3.executeUpdate()
          })
          ps3.close()
          connection.close()
          }
        )
      })




    }


  }
}
