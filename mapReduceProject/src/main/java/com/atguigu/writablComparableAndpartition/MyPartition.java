package com.atguigu.writablComparableAndpartition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition extends Partitioner<FlowBeen, Text> {
    @Override
    public int getPartition(FlowBeen flowBeen, Text text, int numPartitions) {
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
