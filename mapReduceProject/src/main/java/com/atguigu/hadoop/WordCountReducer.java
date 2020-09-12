package com.atguigu.hadoop;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * KEYIN:reducer输入的key和map过程的输出参数一致
 * VALUEIN:reducer输入的value和map过程的输出参数一致
 * KEYOUT:reducer的输出key值
 * VALUEOUT:reducer输出的value
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    IntWritable outV=new IntWritable();

    /**
     *
     * @param key map阶段输出的key
     * @param values map阶段输出的相同key值的value组
     * @param context 上下文
     * @throws IOException io异常
     * @throws InterruptedException 打断异常
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable value : values) {
            int i=value.get();
            sum+=i;
        }
        outV.set(sum);
        // 输出
        context.write(key,outV);
    }
}
