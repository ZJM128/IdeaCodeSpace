package com.atguigu.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/*
*@Description:手动提交offset,需要设置重置offset,提交成功后会返回offset+1的位置
*@author:zhijm
*@Date:2020/8/11 21:03
*/
public class HandleConsumer {
    public static void main(String[] args) {
        // 1创建配置文件对象
        Properties properties=new Properties();
        // 2 配置kafka连接信息
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        //3 指定消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test2");
        // 4 设置是否自动提交偏移量
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        //4.重置offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 6设置key的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        //7 设置value的反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        // 8 创建消费者
        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(properties);
        // 9设置消费者的订阅主题信息
        List<String>list = new ArrayList<>();
        list.add("four");
        //list.add("three");
        consumer.subscribe(list);
        while(true) {
            // 10使用消费者对象拉取信息 拉取一批数据 以主题的分区进行拉取
            final ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("topic:"+record.topic()+
                        "--partition:"+record.partition()+
                        "--offset:"+record.offset()+
                        "--key:"+record.key()+
                        "--value:"+record.value());
            }
            // 11 (1)同步提交，当前线程会阻塞直到offset提交成功
           consumer.commitSync();

            // (2) 手动提交offset 异步提交
            /*consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                //这是个回调函数 当offset成功存储的时候会返回这个offsets,如果不成功存储返回exception
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    System.out.println(offsets);
                }
            });*/
        }

    }
}
