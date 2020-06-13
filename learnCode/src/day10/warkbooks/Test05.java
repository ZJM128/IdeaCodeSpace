package day10.warkbooks;

public class Test05 {
    public static void main(String[] args) {
        A a2 = new B();
        B b = new B();
        D d = new D();
        System.out.println("(2)" + a2.show(d));// (2)B and A
        System.out.println("(4)" + b.show(d)); // (4)B and B
    }
}
class A {
    public String show(A obj) {
        return "A and A";
    }
}
class B extends A {
    public String show(B obj) {
        return "B and B";
    }

    public String show(A obj) {
        return "B and A";
    }
}
class D extends B {
}