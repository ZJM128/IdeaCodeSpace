package com.atguigu.writableTest.flowCount01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FlowCountDriver {
    public static void main(String[] args) throws Exception {

        Configuration configuration=new Configuration();
        final Job job = Job.getInstance(configuration);

        job.setJarByClass(FlowCountDriver.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBeen.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeen.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        final boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);

    }
}
