package atguigu.com;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TimeStampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
         byte[] body = event.getBody();
         String log = new String(body);
         Map<String, String> headers = event.getHeaders();
        try {
              JSONObject jsonObject = JSON.parseObject(log);
              Long ts = jsonObject.getLong("ts");

              // 使用默认的时间戳 key:timestamp
             // headers.put("timestamp",ts+"");
              // 自定义key
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
            final String date = format.format(new Date(ts));
            headers.put("date",date);
            return event;
         }catch (Exception e){
           return null;
         }

    }
    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
