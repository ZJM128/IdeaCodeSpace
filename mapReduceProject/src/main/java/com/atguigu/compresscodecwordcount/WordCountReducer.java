package com.atguigu.compresscodecwordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
    IntWritable outK=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        // 统计总数
        for (IntWritable value : values) {
            final int i = value.get();
            sum+=i;
        }
        outK.set(sum);
        // 输出结果
       context.write(key,outK);
    }
}
