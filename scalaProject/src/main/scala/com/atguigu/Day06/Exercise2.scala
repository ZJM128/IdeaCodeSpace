package com.atguigu.Day06

import com.alibaba.fastjson.JSON
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod

import scala.io.Source

/**
 * "D:\\input\\pmt.json"
 * 统计各省市的原始请求数
 */
object Exercise2 {

  def main(args: Array[String]): Unit = {
    // 1 读取数据

    val source = Source.fromFile("D:/input/pmt.json","utf-8")
    val list = source.getLines().toList.take(100)
    source.close()
    // 2 三部曲:过滤 去重 列裁剪
    // 过滤数据
    list.filter(x=>{
      val jSONObject = JSON.parseObject(x)
      var ip=jSONObject.getString("ip")
      ip!=null && ip!=""
    })
    // 列裁剪
      .map(y=>{
        val jSONObject = JSON.parseObject(y)
        val ip = jSONObject.getString("ip")
        val processnode = jSONObject.getInteger("processnode")
        var requestmode= jSONObject.getInteger("requestmode")
        // 通过ip获取省份,城市
        val url = "https://restapi.amap.com/v3/ip?key=f75418e64363b8a96d3565108638c5f1&ip=%s".format(ip)
        val json = getData(url)
        val result = JSON.parseObject(json)
        var province:String=null
        var city:String=null
        if (result!=null){
          province=result.getString("province")
          city=result.getString("city")
        }
        (ip,province,city,processnode,requestmode)
      })
      // 过滤不符合要求的
      .filter({
        case   (ip,province,city, _, _)=>
          null!=province && null!= city && province!="" && province!="[]" && city!=""
      })
    // 按照省份,城市进行分组
      .groupBy({
        case  (ip,province,city, _, _)=>(province,city)
      })
    // 统计原始请求数
      .map(x=>{
        var (province,city)=x._1
        val size = x._2.count {
          case (ip, province, city, processnode, requestmode) =>
            processnode == 1 && requestmode >= 2
        }
        (province,city,size)
      })
    // 结果展示
      .foreach(println)
  }

  def getData(url:String) ={
    // 1创建Http.cilent
    val client = new HttpClient()
    // 2 创建methode
    val method = new GetMethod(url)
    // 3 发起请求
    val code = client.executeMethod(method)
    // 4 判断是否请求成功,返回对应的数据
    if (code==200){
      method.getResponseBodyAsString
    }else{
      null
    }
  }
}
