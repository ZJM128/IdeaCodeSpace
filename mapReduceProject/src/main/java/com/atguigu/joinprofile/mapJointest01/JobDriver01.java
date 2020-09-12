package com.atguigu.joinprofile.mapJointest01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class JobDriver01 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(JobDriver01.class);
        job.setMapperClass(JobMapper01.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputtable2"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output11"));

        // 设置reducer的个数为零
        job.setNumReduceTasks(0);
        // 将小文件加载到内存缓存中
       job.addCacheFile(new URI("file:///D:/input/inputtable/pd.txt"));
        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);

    }
}
