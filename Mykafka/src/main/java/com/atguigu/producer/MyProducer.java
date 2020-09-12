package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
/*
*@Description:参数手动输入
*@author:zhijm
*@Date:2020/8/11 19:10
*/
public class MyProducer {

    public static void main(String[] args) {

        // 1,创建配置文件
        Properties properties = new Properties();
        // 2,连接kafka集群 broker-list
        properties.put("bootstrap.servers", "hadoop102:9092");
        // 3,设置ACK的等级 all=-1
        properties.put("acks", "all");
        // 4 发送重试的次数
        properties.put("retries", 1);
        // 5,批次的大小,也就是Batch的大小,默认16kb,满了就被sender拉取到broker的分区中进行存储
        properties.put("batch.size", 16384);
        //  6,等待时间,默认是0,生成者把数据往Batch中发送,sender等待多久就拉取Batch 单位毫秒
        properties.put("linger.ms", 1);
        // 7,RecordAccumulator缓冲区大小 默认32M,这个是同享的缓冲区,也就是每个Batch缓存的地方
        properties.put("buffer.memory", 33554432);
        // 8,设置key的序列化,要走序列化器,方便数据在网络中传输
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 9,设置value的序列化,要走序列化器,方便数据在网络中进行传输
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 10,创建生产者的客户端对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {
            // 11,采用生产者对象发送以ProducerRecord封装的数据
            producer.send(new ProducerRecord<String, String>("one", "good-->" + i));
        }
        // 12, 关闭客户端对象 ,这个close会在关闭前把所有的数据处理完再推出生产者,
        //  也就是往Batch中发送,然后给sender拉取存储在broker的分区中
        producer.close();


    }
}
