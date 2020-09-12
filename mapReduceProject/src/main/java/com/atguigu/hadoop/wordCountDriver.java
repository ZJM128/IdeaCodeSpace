package com.atguigu.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class wordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 创建job对象
        Configuration configuration=new Configuration();
        final Job job = Job.getInstance(configuration);
        //  关联本Driver的job
        job.setJarByClass(wordCountDriver.class);
        // 关联 map和reducer的job
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置map的输出key和values类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置最终的输出key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入,输出路径
        FileInputFormat.setInputPaths(job,new Path("D:\\hadooptestfile\\word.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\out"));
        // 提交job
        final boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);


    }
}
