package day10.test15;

public class Man extends person{
    int age=20;
    public void eat(){
        System.out.println("男人吃饭");
    }

    public void toilet(){
        System.out.println("男人上洗手间");
    }
    public void smoking(){
        System.out.println("吸烟");
    }

    public void show(Man man){
        man.eat();
    }

    public void print(){
        System.out.println(age);
    }
}
