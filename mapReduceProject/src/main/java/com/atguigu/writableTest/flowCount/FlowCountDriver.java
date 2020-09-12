package com.atguigu.writableTest.flowCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FlowCountDriver {
    public static void main(String[] args) throws Exception {
        // 创建job对象
        Configuration configuration=new Configuration();
        final Job job = Job.getInstance(configuration);
        // 关联本类Driver的job
        job.setJarByClass(FlowCountDriver.class);
        // 关联Mapper和Reducer的job
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        // 设置map的输出kv的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBeen.class);
        // 设置mr最终输出的kv的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeen.class);
        // 设置输入,输出路径
        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputflow"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output15"));
        // 提交job
        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }
}
