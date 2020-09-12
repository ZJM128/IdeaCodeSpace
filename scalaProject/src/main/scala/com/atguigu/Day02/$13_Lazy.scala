package com.atguigu.Day02

/**
 * 懒加载
 * lazy延迟加载功能是编译器在编译时产生大量的方法进行调用所实现
 * 用到数据的时候加载数据
 */
object $13_Lazy {
  def main(args: Array[String]): Unit = {

    def func()={
      val sql=
        """
          |select *
          |from student
          |where name like "%张%"
          |""".stripMargin
      println("#"*20)
      sql
    }

    // 用到f变量的时候再去执行方法
    lazy val f= func()
    // 此处逻辑执行时间一个小时
    println("执行一个小时")
    // 再使用从数据库中查询出来的数据
    println(f)
  }
}
