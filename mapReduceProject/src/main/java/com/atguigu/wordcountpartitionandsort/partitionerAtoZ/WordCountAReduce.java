package com.atguigu.wordcountpartitionandsort.partitionerAtoZ;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountAReduce extends Reducer<Text, IntWritable,Text,IntWritable> {

    private IntWritable outV=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
         int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        outV.set(sum);
        // 写到磁盘中
        // 思考:是处理完一组数据就往磁盘中写入还是先缓存,应该是处理完就往磁盘中写入,会进行多次IO
        context.write(key,outV);
    }
}
