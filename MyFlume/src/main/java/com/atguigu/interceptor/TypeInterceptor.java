package com.atguigu.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeInterceptor implements Interceptor {
    ArrayList<Event>listEvent;
    @Override
    public void initialize() {
        listEvent=new ArrayList<>();
    }

    /**
     * 单个event处理
     * @param event 事件
     * @return 返回事件event
     */
    @Override
    public Event intercept(Event event) {
        // 获取head
        Map<String, String> headers =event.getHeaders();
        // 获取body
        byte[] bytes = event.getBody();
        //转为string
         String body = new String(bytes);
        // 判断是否包含good
        if(body.contains("good")){
            // 如果包含,在头信息加上值为good的键值对
            headers.put("type","good");
        }else{
            // 如果不包含,在头信息加上值为noGood的键值对
            headers.put("type","noGood");
        }
        // 直接返回event
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        // 先把listEvent清空
        listEvent.clear();
        for (Event event : list) {
            listEvent.add(intercept(event));
        }
        return listEvent;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new TypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
