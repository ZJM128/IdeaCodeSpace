package com.atguigu.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
/*
*@Description:自动提交偏移量,当新创建新消费者组得时候,会默认自动保存主题最近一次offset
*@author:zhijm
*@Date:2020/8/11 20:47
*/
public class MyConsumer {
    public static void main(String[] args) {
        // 1,创建配置文件对象
        Properties properties=new Properties();
        // 2,设置连接kafka集群的信息
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        // 3,指定消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"zhijm");
        // 4,设置指定自动提交偏移量 enable commit 默认为true
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        // 5,设置每次自定提交偏移量的时间间隔 单位毫秒 默认为5000 5秒提交一次
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        // 6,设置key的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        // 7,设置value的反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        // 8,创建一个消费者对象
        KafkaConsumer<String,String>consumer=new KafkaConsumer<String, String>(properties);
        // 9,设置消费者订阅的主题,可以订阅多个主题,所以可以用list
        ArrayList<String> list=new ArrayList<>();
        list.add("four");
       // list.add("three");
        consumer.subscribe(list);
        // 10,使用消费者对象拉取消息 方法获取表示标准秒数的Duration
        while (true) {
            //此时消费者一直在轮询监控分区是否有数据,没有数据就等待timeout时间 如果主题没有数据则等待1秒再轮询
            final ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("topic:"+record.topic()+
                        "--partition:"+record.partition()+
                        "--offset:"+record.offset()+
                        "--key:"+record.key()+
                        "--value:"+record.value());
            }
        }
        // 此时消费者一直在轮询监控分区是否有数据,没有数据就等待timeout时间
    }
}
