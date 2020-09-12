package com.atguigu.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN:map输入参数key值 longWritable
 * VALUEIN:map输入参数value值 Text
 * KEYOUT; map输出参数key值  Text
 * VALUEOUT:map输出参数value值 intWritable
 *
 * 快捷键 ctrl+o 方法重写
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text outK=new Text();
    IntWritable outV=new IntWritable(1);
    /*
     * @param key 偏移量
     * @param value 输入的值
     * @param context 整个上下文
     * @throws IOException io异常
     * @throws InterruptedException 终止异常
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
       String inStr=value.toString();
       // 按空格分割
        final String[] split = inStr.split(" ");
        for (String s : split) {
            outK.set(s);
            context.write(outK,outV);
        }
    }
}
