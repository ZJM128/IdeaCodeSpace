package day11.work;

public class testT {
    private static int anInt =addInt();
    static {
        print("静态");
    }
    public static int addInt(){
        System.out.println("执行了");
        print("执行吗");
        return ++anInt;
    }
    public static void print(String str){
        System.out.println(str + "->" + anInt);// i=1;
    }

    public static void main(String[] args) {
        testT testT=new testT();
    }
}
