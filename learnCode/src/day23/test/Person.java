package day23.test;

public class Person {
    private int id;
    private String name;
    public int age;
    int num;
    protected int width;

    public void sleep(){
        System.out.println("睡觉");
    }

   private void sleep(String name){
       System.out.println(name+"睡觉");
    }
}
