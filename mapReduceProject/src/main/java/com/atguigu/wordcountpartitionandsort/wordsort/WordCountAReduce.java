package com.atguigu.wordcountpartitionandsort.wordsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountAReduce extends Reducer<WordBeen,Text, Text,WordBeen> {

    private IntWritable outV=new IntWritable();

    @Override
    protected void reduce(WordBeen key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);
        }
    }
}
