package day11.work;

public class Frock {
    private static int currentNum=100000;
    private int serialNumber;

    static {
        System.out.println("静态代码块");
        currentNum=150000;
    }
    public Frock() {
        serialNumber=this.getNextNum();
    }

    public int getNextNum(){
        return currentNum+=100;
    }
    public int getSerialNumber() {
        return serialNumber;
    }
    public static void main(String[] args) {
        /*System.out.println(Frock.currentNum);*/
        Frock frock=new Frock();
        System.out.println(frock.getNextNum());
        System.out.println(frock.getNextNum());
        Frock frock1=new Frock();
        System.out.println(frock1.getSerialNumber());
        Frock frock2=new Frock();
        System.out.println(frock2.getSerialNumber());
        Frock frock3=new Frock();
       System.out.println(frock3.getSerialNumber());

    }


}
