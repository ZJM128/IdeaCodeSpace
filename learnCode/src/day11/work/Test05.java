package day11.work;

public class Test05 {
    public static void main(String[] args) {
        new A(new B());
    }
}
class A {
    public A() {
        System.out.println("A");
    }

    public A(B b) {
        this();
        System.out.println("AB");
    }
}

class B {
    public B() {
        System.out.println("B");
    }
}