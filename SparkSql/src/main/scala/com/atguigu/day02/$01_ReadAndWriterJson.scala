package com.atguigu.day02

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.{SPARK_BRANCH, SparkConf}
import org.junit.{After, Before, Test}

/**
 * 数据以文件形式存在
 *    json
 *    CSV
 *    TSV
 *   数据在Mysql
 *   数据在Hive管理的HDFS上
 *   
 *   默认的读写格式是Parquet
 *
 *   读写Json的方法
 *   1.json的读
 *      (1)通用的读:可以使用一个方法,根据传入的参数不同,读取不同的文件的类型
 *          sparkSession.read.format("文件类型").load(文件路径)
 *      (2)专用的读:专门用来读取某种特定文件的方法
 *          sparkSession.read.json(文件路径)
 *   2,json的写
 *      (1)通用的写
 *          df/ds.write.format(文件格式).save(文件路径)
 *      (2)专用的写
 *         df/ds.write.json(文件路径)
 *    3,json读写的注意事项
 *        (1)一个jsonObject对应的str只能独占一行
 *          eg:{name:"张三","age":23}
 */
class $01_ReadAndWriterJson {

  private val conf: SparkConf = new SparkConf().setMaster("local").setAppName("my")
  private val sc: SparkSession = SparkSession.builder().config(conf).getOrCreate()
  // 导入隐式类 注意不能导入非val的类型的变量
  import sc.implicits._

  @Before
  def start(): Unit ={
    val fs = FileSystem.get(new Configuration())
    val path = new Path("output")
    if (fs.exists(path))
      fs.delete(path,true)
  }
  @After
  def stop(): Unit ={
    sc.close()
  }

  /**
   * json的读
   */
  @Test
  def readJson(): Unit ={
    // 通用的读
    val df = sc.read.format("json").load("input/person.json")
   // df.show()
    // 专用的读
    sc.read.json("input/person.json").show()

  }

  @Test
  def writerJson(): Unit ={
    val list = List(Student("lisi", 23), Student("wangwu", 33))

    val rdd = sc.sparkContext.makeRDD(list)
    val ds = rdd.toDS()

    // 通用的写 注意student.json生成的也是目录
    //ds.write.format("json").save("input/student.json")
    ds.write.json("output/student")



  }

  def testDefault() : Unit ={

    // 读默认格式  Parquet
    // sparkSession.read.load("input/employees.json").show()

    val list = List(Student("jack", 20), Student("marry", 21))

    val rdd: RDD[Student] = sc.sparkContext.makeRDD(list, 1)

    val ds: Dataset[Student] = rdd.toDS()

    // 默认以parquet写出
    ds.write.save("person")

  }

}
case class Student(var name:String,var age:Int)