package com.atgugui.day05

import java.sql.{DriverManager, ResultSet}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SPARK_BRANCH, SparkConf, SparkContext}
import org.junit.{After, Before, Test}

/**
 * 读写JDBC
 * 以Mysql为例  ：
 * 读：    从Mysql中读取数据，封装为RDD
 *
 * lass JdbcRDD[T: ClassTag](
 *    sc: SparkContext, // Spark的上下文
 *    getConnection: () => Connection, // JDBC的连接
 *    sql: String, // 编写的sql语句
 *    lowerBound: Long, // 站位符的下界 用来分区
 *    upperBound: Long, // 站位符的上界 用来分区
 *    numPartitions: Int, // 分几个区
 *    mapRow: (ResultSet) => T = JdbcRDD.resultSetToObjectArray _) // 将ResultSet中的单行记录封装为一个T类型
 *      之前：
 *            while(rs.next){    // next不需要调，也不能手动调
 *            rs.getInt;      // 只能调用getInt等
 *            rs.getString
 *      }
 *
 */
class $09_JDBCReadAndWrite{

  private val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("spark"))

  @Before
  def start(): Unit = {
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path, true)
  }

  @After
  def stop(): Unit = {
    sc.stop()
  }

  /**
   * 从mysql中读取表的数据
   */
  @Test
  def test01(): Unit ={

    val rdd = new JdbcRDD[User](
      sc,
      () => DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root"),
      "select * from user where  ? <= id and id <= ?",
      1,
      4,
      1,
      (rs: ResultSet) => User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"))
    )

    rdd.saveAsTextFile("output")
  }

  /**
   * 写入mysql数据库中
   */
  @Test
  def test02()={
    val list = List(User(1, "zhansan", 90), User(2, "lisi", 99), User(3, "wangwu", 100),User(4, "zhaoliu", 89))
    val rdd = sc.makeRDD(list,2)
    rdd.foreachPartition(iter=>{
      // 创建连接
      val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root")
      // 编写sql
      var sql="insert into  user(id,name,age) values(?,?,?)"
      val statement = connection.prepareStatement(sql)
      iter.foreach(User=>{
        statement.setInt(1,User.id)
        statement.setString(2,User.name)
        statement.setInt(3,User.age)
        statement.execute()
      })

      // 关闭资源
      statement.close()
      connection.close()
    })




  }

}
case class User (id:Int,name:String,age:Int)