package com.atguigu.wordcount02;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 将字符串进行分的操作
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text outK=new Text();
    private IntWritable  outV=new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String words = value.toString();
        final String[] list = words.split(" ");
        for(String word:list){
            outK.set(word);
            context.write(outK,outV);
        }
    }
}
