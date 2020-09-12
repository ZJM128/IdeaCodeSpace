package com.atguigu.Day06

import com.alibaba.fastjson.JSON
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod

import scala.io.Source

/**
 * "D:\\input\\pmt.json"
 * 统计各省市的原始请求数
 */
object Test2 {

  def getData(url:String) ={
    // 获取HttpClient客户端
    val client = new HttpClient()
    // 获取根据url创建GetMethod方法
    val getMethod = new GetMethod(url)
    // 请求数据 获取返回状态
    val code = client.executeMethod(getMethod)
    // 判断状态,并返回值
    if (code==200)getMethod.getResponseBodyAsString else null
  }
  def main(args: Array[String]): Unit = {
    getAllProvince()
  }
  def getAllProvince(): Unit ={
    val source = Source.fromFile("D:/input/pmt.json")
    val list = source.getLines().toList.filter(!_.equals(""))
    source.close()
    // 转换格式,过滤,列裁剪,去重
    list.filter(line=>{
      val jsonObject = JSON.parseObject(line)
      var ip=jsonObject.getString("ip")
      ip!="" && ip!=null
    }).map(line=>{
      val data = JSON.parseObject(line)
      var ip=data.getString("ip")
      var url="https://restapi.amap.com/v3/ip?key=f75418e64363b8a96d3565108638c5f1&ip=%s".format(ip)
      // 请求接口获取数据
      val json = getData(url)
      val resultData = JSON.parseObject(json)
      resultData.getString("")
      var province:String=null
      var city:String=null
      if (resultData!=null){
        province=resultData.getString("province")
        city=resultData.getString("city")
      }
      val processnode = data.getInteger("processnode")
      var requestmode= data.getInteger("requestmode")
      (ip,province,city,processnode,requestmode)
    })
    // 过滤 province和city 为空的 使用模式匹配
      .filter({
        case (ip,province,city,_,_)=>
          province!=null && province!="" && province != "[]" && city!=null && city!=""
      })
    // 以省市进行分组
      .groupBy({
       case (ip,province,city,processnode,requestmode)=>
         (province,city)
      })
    // 筛选出processnode=1 && requestmode>=2 的数据
      .map(line=>{
        var (province,city)=line._1
        val size = line._2.count {
          case (ip, province, city, processnode, requestmode) =>
            processnode == 1 && requestmode >= 2
        }
        (province,city,size)
      })
    // 遍历
      .foreach(println)
  }
}
