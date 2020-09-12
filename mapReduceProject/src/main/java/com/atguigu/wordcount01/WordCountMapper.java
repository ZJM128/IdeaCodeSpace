package com.atguigu.wordcount01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text outK=new Text();
    private IntWritable outV=new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String words = value.toString();
        final String[] wordArr = words.split(" ");
        for (String word : wordArr) {
            outK.set(word);
            // 每一个key都有一个数值1
            context.write(outK,outV);
        }
    }
}
