package com.atguigu.compresscodecwordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建job对象
        Configuration configuration=new Configuration();
        // 开启map端输出压缩
        configuration.set("mapreduce.map.output.compress","true");
        //设置map端输出压缩方式
        configuration.set("mapreduce.map.output.compress.codec","org.apache.hadoop.io.compress.DefaultCodec");
        final Job job = Job.getInstance(configuration);
        // 如果不设置InputFormat，它默认用的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        //虚拟存储切片最大值设置4m
        CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
        // 关联本Driver的job
        job.setJarByClass(WordCountDriver.class);
        // 关联 mapper和reducer的job
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 设置mapper的输出的key和value的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置最终输出大的key和value的数据类型
        job.setOutputValueClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //job.setNumReduceTasks(2);
        // 设置输入,输出路径 输入的文件也是压缩的
        FileInputFormat.setInputPaths(job,new Path("D:\\compresscodes"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output25"));

        // 设置reduce端输出压缩开启
        FileOutputFormat.setCompressOutput(job,true);
        // 设置压缩的方式
        FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

        // 提交job
        final boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
