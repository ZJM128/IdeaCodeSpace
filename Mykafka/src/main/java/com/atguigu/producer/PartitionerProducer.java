package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
/*
*@Description:自定义分区器 对数据分到指定的分区中进行存储
*@author:zhijm
*@Date:2020/8/11 20:09
*/
public class PartitionerProducer {

    public static void main(String[] args) {
        // 1 创建配置文件
        Properties properties = new Properties();
        // 2 设置kafka连接信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        // 3 设置ack等级
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        // 4 设置重连的次数
        properties.put(ProducerConfig.RETRIES_CONFIG,1);
        // 5设置等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        // 6设置批容量batch
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        // 7设置缓冲大小 buffer
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        // 8 设置key的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //9 设置value的序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 设置自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.atguigu.partitioner.MyPartitioner");
        // 10 创建生产者对象
        KafkaProducer<String,String>producer=new KafkaProducer<String, String>(properties);
        // 11 使用生产者对象发送数据
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("four","haha"+i),(metadata,exception)->{
                if(exception==null){
                    System.out.println("topic:" + metadata.topic()
                            + " ---partition:" + metadata.partition()
                            + " --offset" + metadata.offset()
                    );
                }
            });
        }
        //12关闭生产者对象
        producer.close();
    }
}
