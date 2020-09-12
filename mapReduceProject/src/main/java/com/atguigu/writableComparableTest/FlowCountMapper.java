package com.atguigu.writableComparableTest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable,Text,FlowBeen,Text> {

    private FlowBeen outK=new FlowBeen();
    private Text outV=new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final String[] words = line.split("\t");
        // 手机号为value
        outV.set(words[0]);
        // 封装key
        outK.setUpFlow(Long.parseLong(words[1]));
        outK.setDownFlow(Long.parseLong(words[2]));
        outK.setSumFlow();
        // 往环形缓冲区中写入数据

        context.write(outK,outV);
    }
}
