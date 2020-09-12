package com.atguigu.etltest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter("ELTInfo","total").increment(1);
       // 获取输入的每一行的数据
        final String line = value.toString();
        // 判断该行长度是否大于11
       boolean flag= checkLength(line,context);
        // 大于11就输出到文件中
       if(flag){

           context.write(value,NullWritable.get());
       }

    }

    private boolean checkLength(String line,Context context) {
        if (line.length()>11){
            context.getCounter("ELTInfo","map").increment(1);
            return true;
        }else{
            context.getCounter("ELTInfo","clear").increment(1);
            return false;
        }
    }
}
