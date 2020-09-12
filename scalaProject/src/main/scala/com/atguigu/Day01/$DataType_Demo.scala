package com.atguigu.Day01

/**
 * Any:所有类的父类
 *   AnyVal:值类型
 *      Byte, Short,Int,Long,Float,Double,Char,Boolean
 *      Unit:只有一个实例(),相当与java的void
 *      StringOps:符对string的补充
 *    AnyRef:引用类型
 *      String,集合,scala其他对象,java其他对象
 *      Null: 是所有引用类型的子类,只有一个实例null
 *   Nothing:所有类型的子类,没有实例,不能初始化,一般用于方法异常之后返回的数据
 *如果想用null对象引用类型赋值初始化值,必须写明变量的类型
 *
 * 数字与字符的转换
 *  1 数字转字符串
 *    1 插值表达式
 *    2数字+""
 *    3数字.toString()
 *   2,字符串转数字:toXXX方法转换
 *
 *   数字间不同类型的转换
 *    1,精度小的能够自动转为精度大的
 *    2,精度大转精度小的:toXXX
 */
object $DataType_Demo {
  def main(args: Array[String]): Unit = {
    def strToNum(): Unit ={
      // 数字转字符串
      val number=23;
      val num_str=number.toString
      val num_str1=number+""
      val num_str2=s"${number}"
      val print_str=
        s"""
           |${num_str},
           |${num_str1},
           |${num_str2},
           |""".stripMargin
      print(print_str)
    }

    def strToNun(): Unit ={
      // 字符串转数字
      val str="12"
      val str_num=str.toInt
      println(str_num)
    }

  def longToInt(): Unit ={
    // 精度大的转为精度小的 需要使用toXXX
    val num_long:Long=12
    val num_int:Int=num_long.toInt
    println(num_int)

    // 精度小的转为精度大的 自动转
    val num_int1:Int=23
    val num_long1:Long=num_int1
    println(num_long1)
  }

  def valData(): Unit ={
   //声明就要初始化
   /*val init_str 编译报错
   println(init_str)
   */

   // val 关键词修饰时,不能改变值
   /* val notChange_num=1234
    notChange_num=990 编译报错
    */
    var name ='a'
    val number:Int=name
    println(number)
  }
    valData()
  }
}
