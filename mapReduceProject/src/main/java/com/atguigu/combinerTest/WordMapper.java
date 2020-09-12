package com.atguigu.combinerTest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private Text outK=new Text();
    private IntWritable outV=new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        final String line = value.toString();
        final String[] words = line.split(" ");
        for (String word : words) {
            outK.set(word);
            context.write(outK,outV);
        }

    }
}
