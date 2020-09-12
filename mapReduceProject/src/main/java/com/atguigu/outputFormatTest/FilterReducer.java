package com.atguigu.outputFormatTest;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FilterReducer extends Reducer<Text, NullWritable,Text,NullWritable> {

    private Text outK=new Text();
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

         String line = key.toString();
         outK.set(line+"\r\n");
         context.write(outK,NullWritable.get());
    }
}
