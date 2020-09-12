package com.atguigu.wordcountpartitionandsort.wordsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WordSortPartitioner extends Partitioner<WordBeen, Text> {
    @Override
    public int getPartition(WordBeen wordBeen, Text text, int numPartitions) {
        final String word = text.toString();
        final char headLetter = word.charAt(0);
        if('a'<=headLetter && headLetter<'q'){
            return 0;
        }else{
            return 1;
        }
    }
}
