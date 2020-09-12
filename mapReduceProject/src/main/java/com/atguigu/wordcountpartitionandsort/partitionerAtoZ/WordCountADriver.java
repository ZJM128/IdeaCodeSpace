package com.atguigu.wordcountpartitionandsort.partitionerAtoZ;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WordCountADriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Job job = Job.getInstance(new Configuration());

        job.setJarByClass(WordCountADriver.class);
        job.setMapperClass(WordCountAMapper.class);
        job.setReducerClass(WordCountAReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputword"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadooptestfile\\output23"));
        // 设置自定义的分区器
        job.setPartitionerClass(WordAPartitioner.class);
        // 设置相应的reducesTasks线程,如果不设置则默认为1个 全部数据写入0区
        job.setNumReduceTasks(2);
        final boolean flag = job.waitForCompletion(true);
        if(flag){
            final Class<?> aClass = Class.forName("com.atguigu.wordcountpartitionandsort.wordsort.WordCountADriver");
            final Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            final Object o = declaredConstructor.newInstance();

            final Method start = aClass.getDeclaredMethod("start",String.class);
            start.invoke(o,"D:\\hadooptestfile\\output23");

        }

    }
    @Test
    public void test() throws Exception {
        final Class<?> aClass = Class.forName("com.atguigu.wordcountpartitionandsort.wordsort.WordCountADriver");
        final Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        final Object o = declaredConstructor.newInstance();

        final Method start = aClass.getDeclaredMethod("start",String.class);
        start.invoke(o,"D:\\hadooptestfile\\output14");
    }
}
