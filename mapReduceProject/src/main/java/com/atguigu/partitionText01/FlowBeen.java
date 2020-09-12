package com.atguigu.partitionText01;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBeen implements Writable {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    //重载一个sumFlow方法 方便计算sunFlow
    public void setSumFlow() {
        this.sumFlow = this.upFlow+this.downFlow;
    }

    public FlowBeen() {
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
            upFlow=in.readLong();
            downFlow=in.readLong();
            sumFlow=in.readLong();
    }

    @Override
    public String toString() {
        return  this.upFlow+"\t" +this.downFlow+"\t"+this.sumFlow;
    }
}
