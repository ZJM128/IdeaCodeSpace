package com.atguigu.writableTest.flowCount01;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text,FlowBeen,Text,FlowBeen> {

    private FlowBeen outV=new FlowBeen();

    @Override
    protected void reduce(Text key, Iterable<FlowBeen> values, Context context) throws IOException, InterruptedException {

        long sumUp=0;
        long sumDown=0;
        for (FlowBeen value : values) {
            sumUp+=value.getUpFlow();
            sumDown+=value.getDownFlow();
        }
        // 封装been
        outV.setUpFlow(sumUp);
        outV.setDownFlow(sumDown);
        outV.setSumFlow();

        context.write(key,outV);
    }
}

