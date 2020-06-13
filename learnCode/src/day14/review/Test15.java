package day14.review;

public class Test15 {
    int a;
    int b;
    String str;
    public void f(){
        a = 0;
        b = 0;
        str = "hello";
        int[] c = {0};
        g(b,c,str);
        System.out.println(a + " " + b + " " + c[0] + "," + str);// 1 0 1 hello
    }
    public void g(int b, int[] c,String s){
        a = 1;
        b = 1;
        c[0] = 1;
        s = "world";
    }
    public static void main(String[] args) {
        Test15 t = new Test15();
        t.f();
    }
}
