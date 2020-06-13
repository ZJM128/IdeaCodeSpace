package day11.work;

public class HelloA {
    public HelloA(){
        System.out.println("Hello A");
    }
    {
        System.out.println(" I A");
    }
}
class HeollB extends HelloA{
    public HeollB(){
        System.out.println("hello B");
    }
    {
        System.out.println("I am B");
    }

    public static void main(String[] args) {
        new HeollB();
    }
}