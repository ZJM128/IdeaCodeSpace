package day10.test;
/*
*@Description:编写Computer类，包含CPU、内存、硬盘等属性，getDetails方法用于返回Computer的详细信息
编写PC子类，继承Computer类，添加特有属性和方法
编写NotePad子类，继承Computer类，添加特有属性和方法
编写Test类，在main方法中创建PC和NotePad对象，分别访问对象中特有的属性、方法，以及从Computer类继承的属性和方法并打印输出
*
*
* 使用多态引用分别创建Computer、PC和NotePad实例，并将实例放在一个Computer类型的数组中。
遍历数组元素，分别调用getDetails方法获取输出结果。
在Computer类中增加price属性表示价格。
将数组元素按价格进行排序，并遍历打印输出。
*@author:zhijm
*@Date:2020/6/3 15:00
*/
public class Computer {
   private  String CPU;
    private String memory;
    private String disk;
    private int price;
    public String getDetail(){
        return  " cpu:"+CPU+"内存: "+memory+"硬盘: "+disk+" 价钱"+price;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public Computer(String CPU, String memory, String disk, int price) {
        this.CPU = CPU;
        this.memory = memory;
        this.disk = disk;
        this.price = price;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setDisk(String disk) {
        this.disk = disk;
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

    public Computer() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Computer(String memory, String disk) {

        this.memory = memory;
        this.disk = disk;
    }


    public Computer(String CPU, String memory, String disk) {
        this.CPU = CPU;
        this.memory = memory;
        this.disk = disk;
    }
}
