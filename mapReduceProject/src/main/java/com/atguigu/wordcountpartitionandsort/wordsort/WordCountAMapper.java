package com.atguigu.wordcountpartitionandsort.wordsort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountAMapper extends Mapper<LongWritable, Text,WordBeen, Text> {

    private WordBeen outK=new WordBeen();
    private Text outV=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final String[] words = line.split("\t");
        outK.setCount(Long.parseLong(words[1]));
        outV.set(words[0]);
        context.write(outK,outV);
    }
}
