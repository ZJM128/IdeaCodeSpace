package com.atguigu.wordcount01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    private IntWritable outV=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // 计算每一个相同的key的一组的value值
        int sum=0;
        for(IntWritable value:values){
            final int i = value.get();
            sum+=i;
        }
        // 得到总和
        outV.set(sum);
        // 输出
        context.write(key,outV);
    }
}
