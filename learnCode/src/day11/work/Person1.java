package day11.work;

public class Person1 {
    private String name;
    private int age;

    public Person1() {
    }

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say(){
        System.out.println("我是:"+name +" "+age+"岁");
    }
}
