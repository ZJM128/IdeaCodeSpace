package java.lang;

/**
 * 双亲委派,沙箱安全机制
 * java.lang.SecurityException: Prohibited package name: java.lang
 */
public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        int i=90;
        System.out.println(i);
    }
}
