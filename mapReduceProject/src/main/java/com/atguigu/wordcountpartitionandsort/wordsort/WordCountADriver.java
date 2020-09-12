package com.atguigu.wordcountpartitionandsort.wordsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountADriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        /*final Job job = Job.getInstance(new Configuration(),"job1");

        job.setJarByClass(WordCountADriver.class);
        job.setMapperClass(WordCountAMapper.class);
        job.setReducerClass(WordCountAReduce.class);

        job.setMapOutputKeyClass(WordBeen.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(WordBeen.class);

        job.setPartitionerClass(WordSortPartitioner.class);
        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path("D:\\hadooptestfile\\output14"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output16"));

        job.waitForCompletion(true);*/
    }
    public void start(String path) throws Exception {
        final Job job = Job.getInstance(new Configuration(),"job1");

        job.setJarByClass(WordCountADriver.class);
        job.setMapperClass(WordCountAMapper.class);
        job.setReducerClass(WordCountAReduce.class);

        job.setMapOutputKeyClass(WordBeen.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(WordBeen.class);

        job.setPartitionerClass(WordSortPartitioner.class);
        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path(path));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output19"));

        job.waitForCompletion(true);
    }
}
