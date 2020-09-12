package com.atguigu.writableComparableTest;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBeen implements WritableComparable<FlowBeen> {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

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

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }
    public void setSumFlow() {
        this.sumFlow = this.upFlow+this.downFlow;
    }

    /**
     * 对总流量进行降序排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(FlowBeen o) {
        if(this.sumFlow>o.sumFlow){
            return -1;
        }else  if(this.sumFlow<o.sumFlow){
            return 1;
        }else {
            return this.upFlow.compareTo(o.upFlow);
        }
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
         out.writeLong(upFlow);
         out.writeLong(downFlow);
         out.writeLong(sumFlow);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
            this.upFlow=in.readLong();
            this.downFlow=in.readLong();
            this.sumFlow=in.readLong();
    }
    @Override
    public String toString(){
        return this.upFlow+"\t"+this.downFlow+"\t"+this.sumFlow;
    }
}
