package com.atguigu.writablComparableAndpartition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text,FlowBeen,Text> {

    private Text outV=new Text();
    private FlowBeen outK=new FlowBeen();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final String[] words = line.split("\t");
        // 设置value值
        outV.set(words[0]);
        // 封装outK
        outK.setUpFlow(Long.parseLong(words[1]));
        outK.setDownFlow(Long.parseLong(words[2]));
        outK.setSumFlow();
        // 向环形缓存区中写入数据
        context.write(outK,outV);
    }
}
