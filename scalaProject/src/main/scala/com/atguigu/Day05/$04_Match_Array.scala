package com.atguigu.Day05

/**
 * 数组的模式匹配
 */
object $04_Match_Array {
  def main(args: Array[String]): Unit = {
    val array = Array[Any](35, "jj", 88)
    array match {
      case Array(a,b,c)=>
        println(s"数组有三个元素,${a},${b},${c}")
      case Array(35,_*)=>
        println("数组的首为是35,后面有多少个元素不知道")
      case Array(x:Int,y:String,c:Int,_*)=>
        println("数组至少有三个元素,分别为int string,int类型")
      case _=>println("没有其他的规则")
    }

    /**
     * Array[Int] => new Int[]{..}
     * Array[Stirng] => new String[]{}
     * arr = new Object[]{}
     *
     * new String[]{}
     */
    array match {
      //case x:Array[Int]=>println("Array[Any]") 报错
      case x:Array[Any]=>println("Array[Any]")
    }
  }
}
