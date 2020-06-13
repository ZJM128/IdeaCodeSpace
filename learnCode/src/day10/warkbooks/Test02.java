package day10.warkbooks;

public class Test02 {
    public static void main(String[] args) {
        Animal animal=new Cat();
        System.out.println(animal.x);//20
    }
}
class Animal{
    int x = 10;
    public Animal(){
        System.out.println("am:"+x);
        this.print();
        x = 20;
    }
    public void print(){
        System.out.println("Animal.x = " + x);
    }
}
class Cat extends Animal{
    int x = 30;
    public Cat(){
        super();
        this.print();
        x = 40;
    }
    public void print(){
        //System.out.println("我执行了");
        System.out.println("Cat.x = " + x);
    }
}