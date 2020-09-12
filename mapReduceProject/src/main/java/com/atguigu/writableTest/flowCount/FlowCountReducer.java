package com.atguigu.writableTest.flowCount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text,FlowBeen,Text,FlowBeen> {

    @Override
    protected void reduce(Text key, Iterable<FlowBeen> values, Context context) throws IOException, InterruptedException {

        FlowBeen outV=new FlowBeen();
        long sumUpFlow=0;
        long sumDownFlow=0;
        // 遍历相同key的一组value值并进行计算
        for (FlowBeen value : values) {
            sumUpFlow+=value.getUpFlow();
            sumDownFlow+=value.getDownFlow();
        }
        // 封装been
        outV.setUpFlow(sumUpFlow);
        outV.setDownFlow(sumDownFlow);
        outV.setSumFlow();
        // 输出
        context.write(key,outV);
    }
}
