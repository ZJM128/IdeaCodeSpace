package day18.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Character {
    @Test
     public void test(){
        List<Man>list = new ArrayList<>();
        list.add(new Man());
        list.add(new Man());
        list.add(new Man());
        list.add(new Man());
        show(list);
        System.out.println("====================");
        List<Woman>list1=new ArrayList<>();
        list1.add(new Woman());
        list1.add(new Woman());
        list1.add(new Woman());
        list1.add(new Woman());
        show(list1);

     }
     public void show(List<? extends Person>list){
         for (Person person : list) {
             person.eat();
             person.sleep();
         }
     }

}
 abstract  class Person{

     public abstract void eat();
     public abstract void sleep();


}

class Man extends Person{


    @Override
    public void eat() {
        System.out.println("男人吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("男人碎觉");
    }
}
class Woman extends Person{

    @Override
    public void eat() {
        System.out.println("女人吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("女人碎觉");
    }
}