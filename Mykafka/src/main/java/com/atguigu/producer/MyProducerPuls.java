package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
/*
*@Description:使用枚举的方式配置参数
*@author:zhijm
*@Date:2020/8/11 19:11
*/
public class MyProducerPuls {
    public static void main(String[] args) {
        // 1,创建配置文件
        Properties properties=new Properties();
        // 2,配置kafka集群信息,连接kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        // 3,配置ACK的等级 一共有 0,1,-1
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        // 4,设置重连的次数 retries
        properties.put(ProducerConfig.RETRIES_CONFIG,1);
        // 5,设置buffer的批次的容量大小 默认16KB
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        // 6,设置等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        // 7,设置recordAccumulator缓冲区的大小 默认32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        // 8,设置key的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 9,设置value的序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 10,创建生产者的客户端对象
        KafkaProducer<String,String> producer=new KafkaProducer<>(properties);
        for (int i = 0; i < 5; i++) {
            // 11,用生产者对象发送以ProducerRecord封装的数据
             producer.send(new ProducerRecord<String, String>("two", "hello" + i));
        }
        // 12,关闭生产者对象
        producer.close();
    }
}
