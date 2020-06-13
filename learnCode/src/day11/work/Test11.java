package day11.work;

public class Test11 {
    static int x, y, z;

    static {
        System.out.println("随类的加载而加载");
        int x = 5;
        x--;
    }

    static {
        x--;
    }

    public static void main(String[] args) {
        System.out.println("x=" + x);
        z--;
        method();
        System.out.println("result:" + (z + y + ++z));
    }

    public static void method() {
        y = z++ + ++z;
    }
}
