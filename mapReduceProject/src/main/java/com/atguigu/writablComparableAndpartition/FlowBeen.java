package com.atguigu.writablComparableAndpartition;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBeen implements WritableComparable<FlowBeen> {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public void setSumFlow() {
        this.sumFlow = this.upFlow+this.downFlow;
    }

    public FlowBeen() {
    }

    @Override
    public int compareTo(FlowBeen o) {
        final int compare = this.sumFlow.compareTo(o.sumFlow);
        if(compare==0){
            return this.upFlow.compareTo(o.upFlow);
        }
        return -compare;
    }

    @Override
    public void write(DataOutput out) throws IOException {
            out.writeLong(upFlow);
            out.writeLong(downFlow);
            out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
            this.upFlow=in.readLong();
            this.downFlow=in.readLong();
            this.sumFlow=in.readLong();
    }

    @Override
    public String toString() {
       return this.upFlow+"\t"+this.downFlow+"\t"+this.sumFlow;
    }
}
