package com.atguigu.day02

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession
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
 *   读写CSV的方法
 *   1.CSV的读
 *      (1)通用的读:可以使用一个方法,根据传入的参数不同,读取不同的文件的类型
 *          sparkSession.read.format("文件类型").load(文件路径)
 *      (2)专用的读:专门用来读取某种特定文件的方法
 *          sparkSession.read.csv(文件路径)
 *   2,json的写
 *      (1)通用的写
 *          df/ds.write.format(文件格式).save(文件路径)
 *      (2)专用的写
 *         df/ds.write.csv(文件路径)
 *    3,CSV读写的注意事项
 *     (1) 默认的分隔符为逗号","如果需要读取其他的分隔符,可以通过.option("seq","分隔符")设置
 *     (2)如果需要读取不同的分割符可以通过以下设计
 *      调用DataFrameReader的option(string,string)为记录读取器设置参数
 *      读取CSV可以设置的参数,可以参考DataFrameReader的608行
 *     (3)默认读取的时候表头是随机生成的,如果需要读取csv中第一行作为表头
 *        可以通过.option("header","true")进行设置
 *   4,写出数据的模式
 *      (1)默认的写出:SaveMode.ErrorIfExists
 *            如果写出路径存在,就报错
 *      (2)overwrite:覆盖写
 *      (3)append:追加写,向输出目的目录追加文件
 *      (4)ignore:忽略本次写出
 *
 *
 */
class $02_ReadAndWriterCSV {

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
   * csv的读 默认以逗号分割
   * 表头是随机生成的
   */
  @Test
  def readCSV(): Unit ={
    // 通用的读
    //val df = sc.read.format("csv").load("input/person.csv")
    // 设置分隔符,文件的第一行为表头
    val df = sc.read.
      format("csv")
      .option("seq","&")
      .option("header","true")
      .csv("input/person.csv").show
   // df.show()
    // 专用的读 m
    //sc.read.csv("input/person.csv").show()

  }

  @Test
  def writerCSV(): Unit ={
    val list = List(Student1("lisi", 23), Student1("wangwu", 33))

    val rdd = sc.sparkContext.makeRDD(list,1)
    val ds = rdd.toDS()

    // 通用的写 注意student1生成的也是目录
    //ds.write.format("csv").save("input/student.json")
    // 专用的写 将ds中的数据以CSV格式写出,指定以_作为分隔符
    ds.write.
    option("seq","+")
      .mode("ignore")
      .csv("output/student1")



  }

}
case class Student1(var name:String,var age:Int)