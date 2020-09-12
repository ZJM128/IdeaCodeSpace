package day10.test;


import org.junit.Test;

public class AnimalTest {
    @Test
    public void test(){
        Animal animal = new Cat();
        animal.show();
        System.out.println(animal.name);

    }

    @Test
    public void test01(){
        Manager manager1=new Manager();
        Manager manager = new Manager("植俊铭",25,new Computer("i9","32g","1t",4567));
        manager.say();
    }

}
