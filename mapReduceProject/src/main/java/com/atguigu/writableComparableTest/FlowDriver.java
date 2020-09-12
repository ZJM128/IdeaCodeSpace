package com.atguigu.writableComparableTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 1,对总流量进行降序分组
 * 2,再对上行流量进行升序排序
 */
public class FlowDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(FlowDriver.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(FlowBeen.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeen.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputflow2"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output7"));

        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }
}
