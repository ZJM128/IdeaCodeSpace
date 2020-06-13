package day10.warkbooks;

public class PolymorphismTest01 {
    public static void main(String[] args) {
        Man man=new Man();//本态
        /*man.eat();
        man.sleep();
        man.smoking();*/

        Person person=man;// 多态-向上转型,系统默认完成
        person.eat();
        person.sleep();
        //person.smoking();// 编译报错 编译看左边 运行看右边
        /*Man man1=(Man) person;
        man1.smoking();
        System.out.println(person==man1); true*/

        //Woman woman =(Woman)person;// 编译没有报错 ,运行的时候报ClassCastException
        // 运行的时候看变量的值,一个是man一个是woman 完全不一样
    }
}
class Person{
    String name;
    int age;
    public void eat(){
        System.out.println("吃饭");
    }
    public void sleep(){
        System.out.println("睡觉");
    }
}

class Man extends Person{
    boolean mustache;
    public void eat(){
        System.out.println("男人吃饭");
    }
    public void sleep(){
        System.out.println("男人睡觉");
    }
    public void smoking(){
        System.out.println("抽烟");
    }
}

class Woman extends Person{
    boolean beauty;
    public void eat(){
        System.out.println("女人吃饭");
    }
    public void sleep(){
        System.out.println("女人睡觉");
    }

    public void shopping(){
        System.out.println("女人爱购物");
    }
}