package com.atguigu.PartitionText;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text,FlowBeen,Text,FlowBeen> {

    private FlowBeen outV=new FlowBeen();
    @Override
    protected void reduce(Text key, Iterable<FlowBeen> values, Context context) throws IOException, InterruptedException {

        long sumUpFlow=0;
        long sumDownFlow=0;
        // 统计每一组key的上行流量和下行流量的总和
        for (FlowBeen value : values) {
            sumUpFlow+=value.getUpFlow();
            sumDownFlow+=value.getDownFlow();
        }
        // 封装FlowBeen
        outV.setUpFlow(sumUpFlow);
        outV.setDownFlow(sumDownFlow);
        outV.setSumFlow();
        // 输出
        context.write(key,outV);
    }
}
