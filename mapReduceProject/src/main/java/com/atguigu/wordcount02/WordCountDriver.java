package com.atguigu.wordcount02;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {
    public static void main(String[] args) throws Exception {
        // 创建job对象

        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        // 关联本Driver的job
        job.setJarByClass(WordCountDriver.class);

        // 关联mapper 和 reducer的job
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 设置mapper输出的key和value的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终的输出的key和value的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 设置输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("D:\\hadooptestfile\\word.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output6"));
        // 提交job
        final boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}

