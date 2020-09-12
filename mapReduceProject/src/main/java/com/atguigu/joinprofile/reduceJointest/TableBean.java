package com.atguigu.joinprofile.reduceJointest;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TableBean implements Writable {

    private String id;
    private String pid;
    private int amount;
    private String name;
    private String flag;

    public TableBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(name);
        out.writeUTF(flag);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id=in.readUTF();
        this.pid=in.readUTF();
        this.amount=in.readInt();
        this.name=in.readUTF();
        this.flag=in.readUTF();
    }

    public String toString(){
        return this.id+"\t"+this.name+"\t"+this.amount;
    }
}
