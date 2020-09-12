package com.atguigu.writableTest.flowCount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBeen implements Writable {

    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBeen() {
    }

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
        return this.upFlow+this.downFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    // 重载setSumFlow方法,方便统计总的流量
    public void setSumFlow() {
        this.sumFlow = this.upFlow+this.downFlow;
    }


    // 实现序列化
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }
    // 实现反序列化
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow=dataInput.readLong();
        this.downFlow=dataInput.readLong();
        this.sumFlow=dataInput.readLong();
    }
    @Override
    public String toString(){
        return upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

}
