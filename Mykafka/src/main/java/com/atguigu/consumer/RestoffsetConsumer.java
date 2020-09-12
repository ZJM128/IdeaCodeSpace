package com.atguigu.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RestoffsetConsumer {
    public static void main(String[] args) {
        // 创建配置文件对象
        Properties properties=new Properties();
        // 配置kafka集群连接信息
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        // 配置消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"good1");
        /**
         * auto.offset.reset: What to do when there is no initial offset in
         * Kafka or if the current offset does not exist any more on the server
         * (e.g. because that data has been deleted):
         * earliest: automatically reset the offset to the earliest offset
         * latest: automatically reset the offset to the latest offset
         * 当你想要重头开始消费 你需要满足3个条件
         * 1.你当前的消费者的消费者组从来没有消费过订阅的主题
         * 2.你当前的消费者的消费者组的offset已经找不到(过期了超过7天对应数据被删除了)
         * 3.你需要将AUTO_OFFSET_RESET_CONFIG置位earliest
         */
        // 重置offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        // 设置指定自动提交偏移量
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        // 设定每次自动提交偏移量的时间间隔
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);
        // 设置key和value的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        // 创建消费者对象
        KafkaConsumer<String,String>consumer=new KafkaConsumer<String, String>(properties);
        // 设置消费者订阅的主题
        List<String>list=new ArrayList<>();
        list.add("four");
        consumer.subscribe(list);
        //通过消费者对象拉取主题的信息
        while (true){
        final ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("topic:" + record.topic() +
                    "--partition:" + record.partition() +
                    "--offset:" + record.offset() +
                    "--key:" + record.key() +
                    "--value:" + record.value());
        }
        }
    }
}
