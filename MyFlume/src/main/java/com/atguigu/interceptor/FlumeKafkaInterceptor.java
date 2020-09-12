package com.atguigu.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 将flume采集的数据按照不同的类型输入到不同的topic中
 *    将日志数据中带有good的，输入到Kafka的four主题中，
 *    将日志数据中带有best的,输入到Kafka的three主题中，
 *   其他的数据输入到Kafka的one主题中
 */
public class FlumeKafkaInterceptor implements Interceptor {
    List<Event>list;
    @Override
    public void initialize() {
        list=new ArrayList<>();
    }

    @Override
    public Event intercept(Event event) {
        // 获取head
         Map<String, String> headers = event.getHeaders();
         // 获取body
         byte[] body = event.getBody();
         if(new String(body).contains("good")){
             headers.put("topic","four");
         }else if(new String(body).contains("best")){
             headers.put("topic","three");
         }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        list.clear();
        for (Event event : events) {
            list.add(intercept(event));
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Build implements Builder{
        @Override
        public Interceptor build() {
            return new FlumeKafkaInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
