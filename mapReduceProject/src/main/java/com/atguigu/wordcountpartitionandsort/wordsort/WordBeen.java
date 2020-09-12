package com.atguigu.wordcountpartitionandsort.wordsort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WordBeen implements WritableComparable<WordBeen> {

    private Long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public WordBeen() {
    }

    @Override
    public int compareTo(WordBeen o) {
        return -this.count.compareTo(o.count);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.count=in.readLong();
    }

    @Override
    public String toString() {
        return this.count+"";

    }
}
