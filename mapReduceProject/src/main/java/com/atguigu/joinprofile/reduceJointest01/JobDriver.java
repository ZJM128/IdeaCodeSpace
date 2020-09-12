package com.atguigu.joinprofile.reduceJointest01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JobDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(JobDriver.class);
        job.setMapperClass(JobMapper.class);
        job.setReducerClass(JobReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JobBean.class);
        job.setOutputKeyClass(JobBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputtable"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output1"));

        job.waitForCompletion(true);
    }
}
