package day11.test;

/*
*@Description:在Frock类中声明静态语句块，语句块中将currentNum的初始值设为150000，作为衣服出厂的序列号起始值，并打印输出该值。
执行TestFrock类的main方法，分别创建三个Frock 对象，验证静态语句块是否只执行一次，以及序列号起始值是否已调整。
*@author:zhijm
*@Date:2020/6/6 16:41
*/
public class Frock {

    public final int NUM;
    private  static int currentNum=100000;
    private int serialNumber;
    static {
        currentNum=150000;
        System.out.println(currentNum);
    }
    {
        serialNumber=1500;
        System.out.println("哈哈哈😄");
    }
    public Frock() {
        this.NUM=12;
        serialNumber=1100;
        System.out.println("难受😣");
        this.serialNumber = getNextNum();
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public static int getNextNum(){
        return currentNum+=100;
    }

    @Override
    public String toString() {
        return "Frock{" +
                "serialNumber=" + serialNumber+
                '}';
    }

    public static void main(String[] args) {
        System.out.println(Frock.getNextNum());
        System.out.println(Frock.getNextNum());
        Frock frock=new Frock();
        Frock frock1=new Frock();
        Frock frock2=new Frock();
        System.out.println(frock);
        System.out.println(frock1);
        System.out.println(frock2);
    }
}
