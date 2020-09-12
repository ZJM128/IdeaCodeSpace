package com.atguigu.combinerTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordDriver {
    public static void main(String[] args) throws Exception {

        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(WordDriver.class);

        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputword"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output11"));
        // 设置需要使用combiner,以及用哪一个类作为combiner
        job.setCombinerClass(WordCombiner.class);

        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }
}
