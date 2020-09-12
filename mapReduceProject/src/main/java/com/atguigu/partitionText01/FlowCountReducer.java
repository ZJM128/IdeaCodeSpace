package com.atguigu.partitionText01;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 统计每一个手机号码的总上行流量和总下行流量
 */
public class FlowCountReducer extends Reducer<Text,FlowBeen,Text,FlowBeen> {

    private FlowBeen outV=new FlowBeen();
    @Override
    protected void reduce(Text key, Iterable<FlowBeen> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow=0;
        long sumDownFlow=0;
        for (FlowBeen value : values) {
            sumUpFlow+=value.getUpFlow();
            sumDownFlow+=value.getDownFlow();
        }
        outV.setUpFlow(sumUpFlow);
        outV.setDownFlow(sumDownFlow);
        outV.setSumFlow();;
        // 通过outputFormat向磁盘文件中写入数据
        context.write(key,outV);
    }
}
