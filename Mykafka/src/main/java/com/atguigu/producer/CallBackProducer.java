package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/*
 *@Description:有回调函数的生产者
 *@author:zhijm
 *@Date:2020/8/11 19:09
 */
public class CallBackProducer {
    public static void main(String[] args) throws InterruptedException {

        // 1,创建配置对象
        Properties properties = new Properties();
        // 2,设置kafka集群连接信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");
        // 3,设置ACK等级
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        // 4,设置重试的次数reties
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        // 5,设置批量大小 batch 默认16KB
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 6,设置等待时间 linger.ms 默认为0 单位毫秒
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 7,设置缓冲大小 默认32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 8,设置 key的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 9,设置value的序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 10,创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        // 11,用生产者对象发送数据
        for (int i = 0; i < 5000; i++) {
            producer.send(new ProducerRecord<>("good", "student" + i), (metadata, exception) -> {
                        // 当exception为空的时候,证明生产者发送消息成功
                        if (exception == null) {
                            System.out.println("topic:" + metadata.topic()
                                    + " ---partition:" + metadata.partition()
                                    + " --offset" + metadata.offset()
                            );
                        }
                    }
            );
        }
        // 12,关闭生产者对象,如果不关闭生产者对象,一毫秒的时候非常短
        producer.close();
    }
}
