package day10.test;

public class Animal {
    String  name="动物";
    public void show(){
        System.out.println("我是动物" + "name"+name);
    }

    public static void main(String[] args) {
        Animal animal=new Dog();
        animal.show();
        if(animal instanceof Dog){
            Dog god=(Dog)animal;
            god.run();
        }

    }
}
class Dog extends Animal {
    public String  name="里";

    public void show(){
        System.out.println("我是cat"+" name:"+name);
    }

    public void run(){
        System.out.println("my name is "+name+" l can run quickly");
    }
}