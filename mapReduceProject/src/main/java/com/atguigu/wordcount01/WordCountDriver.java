package com.atguigu.wordcount01;

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
        configuration.set("mapreduce.job.queuename","hive");
        final Job job = Job.getInstance(configuration);
        // 关联本Driver的job
        job.setJarByClass(WordCountDriver.class);

        // 关联mapper和reducer的job
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 设置mapper的输出key和value的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置最终输出key和value的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        // 提交job true表示将运行进度等信息及时输出给用户，false的话只是等待作业结束
        final boolean flag = job.waitForCompletion(false);
        System.exit(flag?0:1);


    }
}
