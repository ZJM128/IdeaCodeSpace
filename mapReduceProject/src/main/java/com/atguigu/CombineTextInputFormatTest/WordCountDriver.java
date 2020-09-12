package com.atguigu.CombineTextInputFormatTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        final Job job = Job.getInstance(configuration);

        // 关联本类driver的job
        job.setJarByClass(WordCountReducer.class);
        // 关联mapper和reducer的job
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 设置mapper输出的kv的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置mr最终的kv的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置文件输入类型 如果不设置InputFormat默认用的是TextInputFormat 适合做小文件处理
        job.setInputFormatClass(CombineTextInputFormat.class);
        // 虚拟存储切片最大的设置值 单位为字节
        CombineTextInputFormat.setMaxInputSplitSize(job,20971520);
        // 设置输入输出路径
        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputcombinetextinputformat"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output2"));

        //  提交job
        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);

    }
}
