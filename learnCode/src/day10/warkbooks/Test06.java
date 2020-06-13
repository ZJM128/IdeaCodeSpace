package day10.warkbooks;

public class Test06 {
    public static void main(String[] args) {
        Base b = new Sub();
        System.out.println(b.x);
    }
}
class Base{
    int x = 1;
}
class Sub extends Base{
    int x = 2;
}