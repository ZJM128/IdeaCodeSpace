package com.atguigu.joinprofile.mapjointest02;

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

public class MapJoinDriver {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {

        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(MapJoinDriver.class);
        job.setMapperClass(MapJoinMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputtable2"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output32"));
        // 把小文件的数据写入内存缓存中
        job.addCacheFile(new URI("file:///D:/input/inputtable/pd.txt"));
        // 设置reduce的个数,如果reduce的个数不设置就默认为1个
        job.setNumReduceTasks(0);
        // 提交job
        job.waitForCompletion(true);
    }
}
