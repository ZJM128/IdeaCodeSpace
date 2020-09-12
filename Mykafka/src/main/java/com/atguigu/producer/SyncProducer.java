package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/*
*@Description:同步发送:生产者生成一条消息,等到sender把batch中的消息拉取到broker的分区进行存储后返回ack才发送下一条消息
*@author:zhijm
*@Date:2020/8/11 20:22
*/
public class SyncProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,1);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String>producer=new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 5; i++) {
            final Future<RecordMetadata> future = producer.send(new ProducerRecord<>("four", "best" + i), (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("topic:" + metadata.topic()
                            + " ---partition:" + metadata.partition()
                            + " --offset" + metadata.offset()
                    );
                }
            });
            System.out.println("数据发送成功");
            // 线程阻塞,发完一个再发下一条数据
            future.get();
        }
        producer.close();
    }
}
