package com.guigu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class GetAddressName implements Runnable{
    private final static String path="D:\\input\\pmt.json";
    private final static CountDownLatch start=new CountDownLatch(0);
    private final static CountDownLatch timerController=new CountDownLatch(30);
    private final static AtomicInteger count=new AtomicInteger(1);
    private  final static   List<Status> listStatus = Collections.synchronizedList(new ArrayList<>());
    private  static String load=null;
    private   static  List<String> list=new ArrayList<>();
    private final static String URL="https://restapi.amap.com/v3/ip?ip=%s&output=json&key=f75418e64363b8a96d3565108638c5f1";

    public static void main(String[] args) throws IOException, InterruptedException {
        list= getIp();
        System.out.println(list.size());
        start.await();
        final long start = System.currentTimeMillis();
        // 50 --71539
        // 30 --89979
        // 20 --69586
        //------62018
        //------248750
        for(int i=0;i<30;i++){
            new Thread(new GetAddressName()).start();
        }
        timerController.await();
        final long end = System.currentTimeMillis();
        for (Status status : listStatus) {
            System.out.println(status.getCity());
        }
        System.out.println("消耗的时间:"+(end-start));
    }
    @Override
    public void run() {
        while (true) {
            synchronized (GetAddressName.class) {
                final int total = count.get();
                if(total>=list.size()-1){
                    break;
                }
                System.out.println(total);
                count.incrementAndGet();
                load = String.format(URL, list.get(total));
                listStatus.add(get(load));
            }
        }
        timerController.countDown();
    }
    /**
     * get 请求
     * @param url
     * @return
     */
    public static Status  get(String url){
        // 1创建httpClient对象
        HttpClient client=new HttpClient();
        // 2创建method对象
        final GetMethod method = new GetMethod(url);
        // 3发送请求
        try {
            final int code = client.executeMethod(method);
            // 判断请求是否成功
            if(code==200){
                return JSON.parseObject(method.getResponseBodyAsString(),Status.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      throw  new RuntimeException("请求失败");
    }

    public static List<String> getIp(){
        // 读取文件
        List<String>list = new ArrayList<>();
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            fileReader=new FileReader(path);
            bufferedReader=new BufferedReader(fileReader);
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                // 封装成json对象
                final JSONObject jsonObject = JSON.parseObject(line);
                if(jsonObject!=null){
                    list.add(jsonObject.get("ip") + "");
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        start.countDown();
        return list;
    }


}

