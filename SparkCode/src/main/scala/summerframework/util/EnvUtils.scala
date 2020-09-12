package summerframework.util

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object EnvUtils {

  // 创建一个共享数据,利用java的同享线程
  private val scLocal = new ThreadLocal[SparkContext]
  private val sparkSessionLocal = new ThreadLocal[SparkSession]

  // 获取环境
  def getEvn():SparkContext={

    // 从当前线程的共享空间中获取环境对象
    var sparkContext = scLocal.get()

    if (sparkContext==null){
      sparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("mySpark"))
      // 把context 放入线程共享变量中
      scLocal.set(sparkContext)
    }
    // 返回
    sparkContext
  }

  /**
   * 获取sparkSession连接

   */
  def getSparkSession():SparkSession ={
    var session = sparkSessionLocal.get()
    if (session==null){
      val warehouseDir=PropertiesUtil.getValue("dir")
      val pathName=PropertiesUtil.getValue("path")
      val conf = new SparkConf().setMaster("local").setAppName("SparkSession")
      session = SparkSession.builder()
        .enableHiveSupport()
        .config(warehouseDir, pathName)
        .config(conf)
        .getOrCreate()
    }
    session
  }
  // 清除对象
  def clean(): Unit ={
    // 停止环境
    getEvn().stop()
    // 将共享内存中的数据清除
    scLocal.remove()
  }

  def cleanSparkSession(): Unit ={
    getSparkSession().close()
    sparkSessionLocal.remove()
  }

}
