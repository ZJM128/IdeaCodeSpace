package day12.exercise;

public class AbstractTest02 {
    public static void main(String[] args) {
        //Person01 person01=new Person01(); 不能实例化
        Person01 person01=new Chinese01();
        person01.speak();
        person01.show();
    }
}
abstract class Person01{
    private String name;
    private int age;

    public Person01() {
    }

    public Person01(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void speak();

    public abstract void show();
}

class Chinese01 extends Person01{

    @Override
    public void speak() {
        System.out.println("汉语");
    }

    @Override
    public void show() {

        System.out.println("中国人");
    }
}

/**
 * 不全部实现抽象方法的class是抽象类
 */
abstract class USA01 extends Person01{
    public abstract void speak();

    public abstract void show();

    //public abstract  final void show();
    // public abstract static void show();
    // private abstract void show();
}

/**
 * 抽象方法可以没有抽象方法
 */
abstract  class Animal{
    public void fly(){

    }
}

abstract class Person02{
    public abstract void show();
}