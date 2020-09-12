package com.atguigu.joinprofile.reduceJointest01;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 根据pid进入reduce的时候根据pid进行分组输出
 */
public class JobMapper extends Mapper<LongWritable, Text,Text, JobBean> {

    private String name;
    private Text outK=new  Text();
    private JobBean outV=new JobBean();
    /**
     * 获取文件名
     * @param context  上下文信息
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
         FileSplit split = (FileSplit) context.getInputSplit();
         name = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String[] split = value.toString().split("\t");
        if(name.contains("order")){
            outK.set(split[1]);
            outV.setId(split[0]);
            outV.setPid(split[1]);
            outV.setAmount(Integer.parseInt(split[2]));
            outV.setName("");
            outV.setFlag("order");
        }else{
            outK.set(split[0]);
            outV.setId("");
            outV.setPid(split[0]);
            outV.setName(split[1]);
            outV.setAmount(0);
            outV.setFlag("pd");
        }
        context.write(outK,outV);
    }
}
