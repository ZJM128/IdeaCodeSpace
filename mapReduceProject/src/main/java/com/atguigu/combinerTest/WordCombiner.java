package com.atguigu.combinerTest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCombiner extends Reducer<Text, IntWritable,Text,IntWritable> {
    private static int count=0;
    private IntWritable outV=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("============================="+(count++)+"=============================");
        int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        outV.set(sum);
        context.write(key,outV);
    }
}
