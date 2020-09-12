package com.atguigu.partitionText01;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 对手机号的前三位进行分区
 */
public class ProvincePartitioner extends Partitioner<Text,FlowBeen> {

    @Override
    public int getPartition(Text text, FlowBeen flowBeen, int numPartitions) {
         String phone = text.toString();
         phone=phone.substring(0,3);
         switch (phone){
             case "136":return 0;
             case "137":return 1;
             case "138":return 2;
             case "139":return 3;
             default:return 4;

         }
    }
}
