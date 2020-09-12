package com.atguigu.joinprofile.mapjointest02;

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

public class MapJoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    private Text outK=new Text();
    private boolean flag=true;

    // 声明一个map用于存储商品的信息
    HashMap<String,String>map = new HashMap<>();
    /**
     * 从缓存中获取数据,然后存在map中,用于后面字符的拼接,每一个mapTask只执行一次
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 先从缓冲区中获取缓存的文件的路径
        final URI[] cacheFiles = context.getCacheFiles();
        // 创建流对象就行数据的读取
        final FileSystem fis = FileSystem.get(context.getConfiguration());
        // 获取输入流
        final FSDataInputStream in = fis.open(new Path(cacheFiles[0]));
        // 因为hadoop提供的流的api不提供读取一行数据的方法,所以需要封装一个可以读取一行数据的流对象
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
        // 读取每行数据
        String line=null;
        while (StringUtils.isNotEmpty(line=bufferedReader.readLine())){
            final String[] split = line.split("\t");
            map.put(split[0],split[1]);
        }
        // 最后关闭流对象
        bufferedReader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 处理每一行数据
        final String line = value.toString();
        final String[] split = line.split("\t");
        // 获取相应的key值
        String index=split[1];
        // 获取相应的value值
        final String valueMap = map.get(index);
        // 往文件中写出数据
        outK.set(index+"\t"+valueMap+"\t"+split[2]);
        context.write(outK,NullWritable.get());
    }
}
