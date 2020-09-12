package com.atguigu.joinprofile.mapJoin;

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

    private HashMap<String,String> map = new HashMap<>();
    private Text outK=new Text();
    /**
     *读取小表中的数据,存储到map中
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取缓冲中的数据
        final URI[] cacheFiles = context.getCacheFiles();
        //  因为程序需要跑到集群中所以要用hadoop的客户端创建流对象
        final FileSystem fis = FileSystem.get(context.getConfiguration());
        // 创建输入流
        final FSDataInputStream in = fis.open(new Path(cacheFiles[0]));
        // 又因为hadoop提供的api没有直接读取一行的方法,所以需要封装出一种可以读取一行的路流对象
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(in,"UTF8"));
        // 读取数据
        String line=null;
        // 将每一个数据封装到map中
        while (StringUtils.isNotEmpty(line=bufferedReader.readLine())){
            final String[] split = line.split("\t");
            map.put(split[0],split[1]);
        }
         // 最后关闭流对象
        bufferedReader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        final String line = value.toString();
        final String[] split = line.split("\t");

        outK.set(split[1]+"\t"+map.get(split[1])+"\t"+split[2]);
        // 输出到文件中
        context.write(outK,NullWritable.get());
    }
}
