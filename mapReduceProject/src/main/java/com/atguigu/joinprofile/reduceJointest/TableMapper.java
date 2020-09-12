package com.atguigu.joinprofile.reduceJointest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 要根据不同的文件来源的数据进行打标签,封装不一样的数据
 */
public class TableMapper extends Mapper<LongWritable, Text,Text,TableBean> {

    private  String fileName;
    private TableBean tableBean=new TableBean();
    private Text outK=new Text();
    /**
     * 获取输入文件的文件名
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        fileName = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String line = value.toString();
        final String[] words = line.split("\t");
        if(fileName.contains("order")){
            outK.set(words[1]);
            tableBean.setId(words[1]);
            tableBean.setPid(words[0]);
            tableBean.setAmount(Integer.parseInt(words[2]));
            tableBean.setName("");
            tableBean.setFlag("order");
        }else{
            outK.set(words[0]);
            tableBean.setId(words[0]);
            tableBean.setPid("");
            tableBean.setAmount(0);
            tableBean.setName(words[1]);
            tableBean.setFlag("pd");
        }
        context.write(outK,tableBean);
    }
}
