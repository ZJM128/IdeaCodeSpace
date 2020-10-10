package summerframework.code

import java.net.{ServerSocket, Socket}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.{SparkConf, SparkContext}
import summerframework.util.EnvUtils.keepEvn
import summerframework.util.{EnvUtils, PropertiesUtil}

trait Application {
  var envData:Any=null
  def start(s:String)(op: =>Unit)(pathName:String=""): Unit ={
    // 1 初始化环境
   // println(s"$s 环境的初始化成功")
     if (s=="socket"){
       envData=new Socket(PropertiesUtil.getValue("server.host"),PropertiesUtil.getValue("server.port").toInt)
     }else if (s=="serverSocket"){
       envData=new ServerSocket(PropertiesUtil.getValue("server.port").toInt)
     }else if(s=="spark"){
       // 初始化环境
       envData = EnvUtils.getEvn()
       // 删除文件
       if (pathName!=""){
         val path = new Path(pathName)
         val fileSystem = FileSystem.get(new Configuration())
         if (fileSystem.exists(path)){
            fileSystem.delete(path,true)
         }
       }
     }else if(s=="sparkSql"){
       envData=EnvUtils.getSparkSession()
     }else if(s=="sparkStreaming"){
       envData=EnvUtils.getStreamingContext
     } else{
       throw new RuntimeException("参数传入不正确")
     }
    // 2 业务逻辑
    try{
      op
    }catch {
      case exception: Exception=>println("业务逻辑执行失败"+exception.getMessage)
    }

    // 3环境的关闭
   // println(s"$s 环境关闭")
    if (s=="socket"){
      var socket=envData.asInstanceOf[Socket]
      if (!socket.isClosed){
        socket.close()
      }
    }else if (s=="serverSocket"){
      var serverSocket=envData.asInstanceOf[ServerSocket]
      if (!serverSocket.isClosed){
        serverSocket.close()
      }
    }else if (s == "spark"){
     /* val sc = envData.asInstanceOf[SparkContext]
      if (!sc.isStopped){
        sc.stop()
      }*/
      EnvUtils.clean()
    }else if (s == "sparkSql"){
      EnvUtils.cleanSparkSession()
    }else if (s=="sparkStreaming"){
      EnvUtils.keepEvn()
    }
  }
}
