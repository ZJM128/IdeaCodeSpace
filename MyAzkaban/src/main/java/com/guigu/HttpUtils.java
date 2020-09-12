package com.guigu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;

public class HttpUtils {
    /**
     * get请求
     * @param url get请求的路径
     */
    public static void get(String url) throws IOException {
        // 1 创建httpClient
        HttpClient client=new HttpClient();
        // 2 创建Method
        final GetMethod method = new GetMethod(url);
        // 3发起请求
        final int code = client.executeMethod(method);
        // 4 判断请求是否成功
            //404 - url不存在
            //500 - 接口代码报错
            //200 - 请求成功
        if(code==200){
            System.out.println(method.getResponseBodyAsString());
            final JSONObject jsonObject = JSON.parseObject(method.getResponseBodyAsString());
            System.out.println(jsonObject.get("city"));
        }
        // 5打印结果
    }

    /**
     * post 请求
     * @param url post请求路径
     * @param content body信息
     * @throws Exception 异常报错
     */
    public static void post(String url,String content) throws Exception {
        // 1 创建httpClient
        HttpClient client=new HttpClient();
        // 2 创建method
        final PostMethod method = new PostMethod(url);
        // 3 设置body参数
        StringRequestEntity entity=new StringRequestEntity(content,"application/json","utf-8");
        method.setRequestEntity(entity);
        // 4 发起请求
        final int code = client.executeMethod(method);
        // 5 判断请求是否成功
             //404 - url不存在
            //500 - 接口代码报错
            //200 - 请求成功
        if(code==200){
            System.out.println(method.getResponseBodyAsString());
        }
        // 打印结果
    }

    public static void main(String[] args) throws Exception {
        String url = "https://restapi.amap.com/v4/geofence/meta?key=f75418e64363b8a96d3565108638c5f1";
        String json ="{\n" +
                "\"name\": \"测试围栏名称\",\n" +
                "\"center\": \"115.672126,38.817129\",\n" +
                "\"radius\": \"1000\",\n" +
                "\"enable\": \"true\",\n" +
                "\"valid_time\": \"2020-09-19\",\n" +
                "\"repeat\": \"Mon,Tues,Wed,Thur,Fri,Sat,Sun\",\n" +
                "\"time\": \"00:00,11:59;13:00,20:59\",\n" +
                "\"desc\": \"测试围栏描述\",\n" +
                "\"alert_condition\": \"enter;leave\"\n" +
                "}";
        post(url,json);
    }
}
