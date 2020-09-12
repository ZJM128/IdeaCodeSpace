package com.atguigu.PartitionText;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 对手机号进行分组
 */
public class FlowCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final Job job = Job.getInstance(new Configuration());

        // 关联本类driver的job
        job.setJarByClass(FlowCountDriver.class);
        // 关联mapper和reducer的job
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        // 设置mapper的输出kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBeen.class);
        // 设置mr最终的kv的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeen.class);
        // 设置自定义的数据分区
        job.setPartitionerClass(ProvincePartitioner.class);
        // 同时指定相应数量的reducer task的数量,如果是默认或者是设置了为1个则分组不起作用
        job.setNumReduceTasks(5);
        // 设置输入 输出路径
        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputflow"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output4"));
        // 提交job
        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);


    }
}
