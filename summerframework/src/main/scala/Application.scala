trait Application {
  def start(s:String)(op: =>Unit)={
    // 1 初始化环境
    println(s"$s 环境初始化成功")
    // 2 业务逻辑
    op
    // 3 环境的关闭
    println(s"$s 环境关闭")
  }
}
