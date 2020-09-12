package com.atguigu.sink;


import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySink extends AbstractSink implements Configurable {
    // 定义前后缀
    private String prefix;
    private String subfix;
    // 定义longger日志
    Logger logger = LoggerFactory.getLogger(MySink.class);

    /**
     * 业务逻辑的书写
     *
     * @return 返回状态
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {

        // 1,定义状态
        Status status = null;
        // 2,获取与sink绑定的channel
        final Channel channel = getChannel();
        //3,从channel获取事务
        Transaction transaction = channel.getTransaction();
        // 4,开启事务
        transaction.begin();
        try {
            // 5,从channel中take event
            final Event event = channel.take();
            // 6判断event是否为null
            if (event != null) {
                // 6.1 不为空往日志中打印信息
                logger.info(prefix + "--" + new String(event.getBody()) + "--" + subfix);

            }
            //7,返回状态
            status = Status.READY;
            // 8,提交事务
            transaction.commit();
        } catch (ChannelException e) {
            // 9打印错误栈的信息
            e.printStackTrace();
            // 10,返回退避状态
            status = Status.BACKOFF;
            // 11,事务的回滚
            transaction.rollback();
        } finally {
            // 12,关闭事务
            transaction.close();
        }
        // 13,返回状态
        return status;
    }

    /**
     * 获取配置的信息
     *
     * @param context
     */
    @Override
    public void configure(Context context) {
        prefix=context.getString("prefix");
        subfix=context.getString("subfix","longlong");
    }
}
