package day12.exercise;

public class B implements A {
    @Override
    public void showA() {
        System.out.println("AAA");
    }

    public static void main(String[] args) {
        B b=new B();
        b.showA();
        b.showB();
    }
}
