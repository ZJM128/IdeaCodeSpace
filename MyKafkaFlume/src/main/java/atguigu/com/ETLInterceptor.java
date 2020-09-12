package atguigu.com;

import com.alibaba.fastjson.JSON;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String json = new String(body);
        try {
            JSON.parse(json);
            return event;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Event> intercept(List<Event> events) {
        events.removeIf(event -> intercept(event) == null);
        return events;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
