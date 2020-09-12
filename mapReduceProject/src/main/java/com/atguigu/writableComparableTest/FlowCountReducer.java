package com.atguigu.writableComparableTest;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class FlowCountReducer extends Reducer<FlowBeen, Text,Text,FlowBeen> {

    @Override
    protected void reduce(FlowBeen key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 要循坏写出到文件中,因为可能有相同的总流量,但是key值不一样的情况
        for (Text value : values) {
            context.write(value,key);
        }
    }
}
