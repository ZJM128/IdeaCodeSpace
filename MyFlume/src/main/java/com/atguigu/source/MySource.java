package com.atguigu.source;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

public class MySource extends AbstractSource implements Configurable, PollableSource {
    private String prefix;
    private String subfix;
    /**
     * 接收数据的主要逻辑
     * 使用for循环生产数据
     * 给数据加上前缀后缀,并且是同过配置文件传进来
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        //  1,定义状态
        Status status=null;
        // 2,读取数据逻辑
        try {
            for (int i = 0; i < 5; i++) {
                // 2.1获取事件event
                final Event event = new SimpleEvent();
                // 2.3往body中封装数据
                event.setBody((prefix+"--"+i+"--"+subfix).getBytes());
                // 2.4把数据发送出去
                getChannelProcessor().processEvent(event);
                // 让数据每2秒发送一次
                Thread.sleep(2000);
            }
            status=Status.READY;
        } catch (InterruptedException e) {
            e.printStackTrace();
            status=Status.BACKOFF;
        }

        return status;


    }

    /**
     * 退避的增长值
     * @return
     */
    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    /**
     * 退避的最大值
     * @return
     */
    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    /**
     * 获取配置信息
     * @param context
     */
    @Override
    public void configure(Context context) {
        prefix=context.getString("prefix");
        subfix=context.getString("subfix","earn");
    }
}
