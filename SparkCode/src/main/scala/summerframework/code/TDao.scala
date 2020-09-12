package summerframework.code

import org.apache.spark.rdd.RDD
import summerframework.util.EnvUtils

/**
 * 数据访问对象,专门负责和关系型数据库进行交互
 */
trait TDao {

  // 传入路径返回相应的RDD
  def readFile(pathName:String): RDD[String] ={
    val context = EnvUtils.getEvn()
    context.textFile(pathName)
  }
}
