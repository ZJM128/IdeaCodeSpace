package day12.exercise;

public class InterfaceTest {
    public static void main(String[] args) {
        AA aa=new Brid();
        aa.show();
        aa.speak();
        Flyer flyer=new Brid();
        flyer.fly();
        Animal01 animal=new Brid();
        animal.show();
    }
}

interface AA{
    public static final int NUm=1;
    public abstract void show();
    String NAME="李白";
     void speak();
   // public AA(){} 不可以有构造器
    //  public void show(){} 不可以有普通方法
    // public void show{ int n} 不可以有局部变量
    //{ int i=0;} 不可以有代码块
}

interface BB{
    int NUM=100;
    void fly();
}

/**
 * 接口可以继承一个或多个接口,不可以继承任何类,包括抽象类
 */
interface Flyer extends AA,BB{
    int NUM=200;
    void fly();
}

interface Runner{
    int NUM=200;
}
class Animal01{
    public void show(){
        System.out.println("我是动物");

    }

}

/**
 * 一个类可以继承一个父类,同时也可以实现一个或多个接口
 * 该类可以被它继承的或实现的接口的引用指向
 */
class Brid extends Animal01 implements Flyer,Runner{

    public static final int NUM = 11;

    @Override
    public void show() {
        System.out.println("中国人");
    }

    @Override
    public void speak() {
        System.out.println("汉语");
    }

    @Override
    public void fly() {
        System.out.println("自由飞翔");
    }
}