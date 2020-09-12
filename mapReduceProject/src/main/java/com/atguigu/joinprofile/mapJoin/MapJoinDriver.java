package com.atguigu.joinprofile.mapJoin;

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

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {

        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(MapJoinDriver.class);
        job.setMapperClass(MapJoinMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);

//        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputtable2"));
//        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output4"));
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        // 把小文件存储到内存缓存区中
        job.addCacheFile(new URI(args[2]));
        // 设置reducer的个数为零,也可以不设置,默认为1个
        job.setNumReduceTasks(0);
        job.waitForCompletion(true);
    }
}
