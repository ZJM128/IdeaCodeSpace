package day09.com.atguigu.work;

/*
*@Description:计算机类
*@author:zhijm
*@Date:2020/6/3 20:55
*/
public class Computer {
    private String CPU;
    private String memory;
    private String disk;

    public Computer() {
    }

    public String getCPU() {
        return CPU;
    }

    public String getMemory() {
        return memory;
    }

    public String getDisk() {
        return disk;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getDetails(){
        return CPU+" "+memory+" "+disk;

    }
}
