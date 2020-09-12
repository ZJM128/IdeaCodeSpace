package com.atguigu.partitionText01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(FlowCountDriver.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBeen.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeen.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputflow"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output5"));
        // 设置分区
        job.setPartitionerClass(ProvincePartitioner.class);
        // 设置reducer的个数
        job.setNumReduceTasks(5);
        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }
}
