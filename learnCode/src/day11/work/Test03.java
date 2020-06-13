package day11.work;

public class Test03 {
    public static void main(String[] args) {
        Father f = new Father();
        Child1 c = new Child1();
    }
}
class Father {
    public Father(){
        System.out.println("father create");
    }
}
class Child1 extends Father{
    public Child1(){
        System.out.println("child1 create");
    }
}