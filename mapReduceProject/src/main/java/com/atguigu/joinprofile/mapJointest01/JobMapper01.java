package com.atguigu.joinprofile.mapJointest01;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

public class JobMapper01 extends Mapper<LongWritable, Text,Text, NullWritable> {

    HashMap<String,String>map = new HashMap<>();
    private Text outK=new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取缓冲区里小表里的数据
        final URI[] cacheFiles = context.getCacheFiles();
        final FileSystem fis = FileSystem.get(context.getConfiguration());
        final FSDataInputStream input = fis.open(new Path(cacheFiles[0]));
        // 需要读取每一行的数据,但hadoop提供的流中没有读入一行的方法,所以需要封装一个字符流
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(input,"UTF8"));
        //处理每一行的数据,存储到map中
        String line=null;
        while(StringUtils.isNotEmpty(line=bufferedReader.readLine())){
            final String[] split = line.split("\t");
            map.put(split[0],split[1]);
        }


        // 最后关闭流对象
        bufferedReader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter("mapGroup","man").increment(1);
        final String line = value.toString();
        final String[] split = line.split("\t");
        // 得到一个map的key 然后从map中获取数
        String index=split[1];
        outK.set(split[1]+"\t"+map.get(index)+"\t"+split[2]);
        context.write(outK,NullWritable.get());
    }
}
