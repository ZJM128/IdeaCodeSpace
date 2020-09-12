package com.atguigu.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
/*
*@Description:自定义分区器
*@author:zhijm
*@Date:2020/8/11 19:50
*/
public class MyPartitioner implements Partitioner {

    @Override
    /**
     * 主要的分区逻辑
     * 有good发往0号分区
     * 没有good发往1号分区
     */
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //分区
       if(new String(valueBytes).contains("good")){
           return 0;
       }else{
           return 1;
       }

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
