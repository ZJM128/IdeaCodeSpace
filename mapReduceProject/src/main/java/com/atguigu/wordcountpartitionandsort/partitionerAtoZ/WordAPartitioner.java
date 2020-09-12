package com.atguigu.wordcountpartitionandsort.partitionerAtoZ;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 进入环形缓冲区就已经分好区,所以这两个参数和mapper的输出类型一致
 */
public class WordAPartitioner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        final String word = text.toString();
        final char headLetter = word.charAt(0);
        if('a'<=headLetter && headLetter<'q'){
            return 0;
        }else{
            return 1;
        }
    }
}
