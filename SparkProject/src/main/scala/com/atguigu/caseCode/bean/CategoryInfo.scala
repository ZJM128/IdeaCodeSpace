package com.atguigu.caseCode.bean

case class CategoryInfo(var CategoryId:String, var cc:Int, var oc:Int, var pc:Int)extends Ordered[CategoryInfo] {
  override def compare(that: CategoryInfo): Int = {
    var result = -this.cc.compareTo(that.cc)
    if (result==0){
      result= - this.oc.compareTo(that.oc)
      if (result==0)
        result= - this.pc.compareTo(that.pc)
    }
    result
  }
}
