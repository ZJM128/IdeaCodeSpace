package com.atguigu.writableTest.flowCount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text,Text,FlowBeen> {

    private Text outK=new Text();
    private FlowBeen outV=new FlowBeen();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final String[] words = line.split("\t");

        // 设置key值
        outK.set(words[1]);
        // 封装been并设置value值
        outV.setUpFlow(Long.parseLong(words[words.length-3]));
        outV.setDownFlow(Long.parseLong(words[words.length-2]));
        outV.setSumFlow();
        // 输出
        context.write(outK,outV);

    }
}
