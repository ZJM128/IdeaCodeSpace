package com.atguigu.writablComparableAndpartition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        final Job job = Job.getInstance(new Configuration());
        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(FlowBeen.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeen.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputflow2"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output18"));

        // 设置分组
        job.setPartitionerClass(MyPartition.class);
        // 设置reducer的个数 默认为一个
        job.setNumReduceTasks(5);
        final boolean flag = job.waitForCompletion(true);

        System.exit(flag?0:1);

    }
}
