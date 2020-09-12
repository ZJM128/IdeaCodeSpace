package com.atguigu.outputFormatTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FilterDriver {

    public static void main(String[] args) throws Exception {
        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(FilterDriver.class);

        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(FilterReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 设置自定义的输出格式
        job.setOutputFormatClass(FilterOutputFormat.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputoutputformat"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output12"));

        job.waitForCompletion(true);
    }
}
