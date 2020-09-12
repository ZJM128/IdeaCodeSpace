package com.guigu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetAddressNameByThread {
    private final static String path="D:\\input\\pmt.json";
    private final static String URL="https://restapi.amap.com/v3/ip?ip=%s&output=json&key=f75418e64363b8a96d3565108638c5f1";
    public static void main(String[] args) {
        getIp();
    }
    public static void getIp(){
        // 读取文件
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            fileReader=new FileReader(path);
            bufferedReader=new BufferedReader(fileReader);
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                // 封装成json对象
                final JSONObject jsonObject = JSON.parseObject(line);
                Thread.sleep(200);
               new Thread(new Thread(){
                   @Override
                   public void run() {
                       get(String.format(URL, jsonObject.get("ip")+""));
                   }
               }).start();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void  get(String url){
        // 1创建httpClient对象
        HttpClient client=new HttpClient();
        // 2创建method对象
        final GetMethod method = new GetMethod(url);
        // 3发送请求
        try {
            final int code = client.executeMethod(method);
            // 判断请求是否成功
            if(code==200){
                System.out.println(JSON.parseObject(method.getResponseBodyAsString()).get("city"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw  new RuntimeException("请求失败");
    }
}
