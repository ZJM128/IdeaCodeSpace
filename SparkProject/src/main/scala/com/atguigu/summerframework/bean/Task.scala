package com.atguigu.summerframework.bean

class Task extends Serializable {
  var data:Int= 0
  var logic:Int=>Int=_
  // 逻辑代码
  def compute()={
    logic(data)
  }
}
