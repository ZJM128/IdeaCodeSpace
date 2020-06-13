package day12.exercise;

public interface A {
    void showA();
    default  void showB(){
        System.out.println("BBB");
    }
}
