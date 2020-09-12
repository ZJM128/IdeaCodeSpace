package com.atguigu.Day05

import scala.swing.event.AdjustingEvent

/**
 * List集合的模式匹配
 */
object $05_Math_List {
  def main(args: Array[String]): Unit = {
    val list = List[Any]("hello",false,2.2,10,30,"hadoop")
   /* list match {
      case List(x)=>println(s"匹配list中只有一个元素的,${x}")
      case List(x:String,y:Boolean)=>
        println("匹配list至少有两个元素,这两个元素的类型为string 和boolean类型")
      case List(x,y,z,_*)=>println(s"匹配List至少有三个元素${x},${y},${z} ")
      case _=>println("没有其他的匹配规则")
    }*/

    //Nil代表空集合
    //var n:List[Any] = Nil
   // n=list
   // println(n)
    // :: 往Nil空集合添加数据
   //var list2=0::2::4::Nil
   // println(list2)

    list match {
      case x::Nil=>println(s"匹配list只有一个元素${x}")
      case (x:String)::(y:Boolean)::tail=>println(s"匹配至少有两个元素 元素类型分别为 string boolean $tail")
      case x::y::z::tail=>println(s"匹配list至少有三个元素${x} ${y} ${z} ${tail}")
    }

    // 泛型的特性:泛型擦除

  /*  Class<?> strListClass = new ArrayList<String>().getClass();
    Class<?> intListClass = new ArrayList<Integer>().getClass();
    // 输出：class java.util.ArrayList
    System.out.println(strListClass);
    // 输出：class java.util.ArrayList
    System.out.println(intListClass);
    // 输出：true
    System.out.println(strListClass.equals(intListClass));*/
   // 匹配的是List的字节码,也就是Class
    list match {
      case x:List[Boolean]=>println("List[Boolean]")
      case x:List[String]=>println("List[String]")
      case x:List[Any]=>println("List[Any]")
    }
    // set 和map的key是没有泛型擦除的
    val map = Map[String, Int]("a" -> 12, ("b", 3))
    map match {
      case x:Map[String,String]=>println("Map[String,String]")
    }
  }
}
