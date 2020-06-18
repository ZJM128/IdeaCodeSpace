package day18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PersonTest {
    @Test
    public void test01(){
       /* Man[]mans=new Man[10];
        for(int i=0;i<10;i++){
            mans[i]=new Man()
;        }
        Person[]person=mans;
        for(Person person1:person){
            person1.eat();
        }*/


        //  不能实现多态
        /*List<Man>list=new ArrayList<>();
        List<Person>list1=list;*/
        List<Man>list1=new ArrayList<>();
        list1.add(new Man());
        list1.add(new Man());
        list1.add(new Man());
        list1.add(new Man());
        /*List<? extends Person>list=list1;
        Iterator<? extends Person> iterator = list.iterator();
        while(iterator.hasNext()){
            iterator.next().eat();
        }*/
        show(list1);
        System.out.println("============================");
        List<Woman>list=new ArrayList<>();
        list.add(new Woman());
        list.add(new Woman());
        list.add(new Woman());
        list.add(new Woman());
        list.add(new Woman());
        Collections.shuffle(list,new Random());//使用指定的随机源随机排列指定的列表
        show(list);

    }

    public static void show(List<? extends Person> list){
        for(Person person:list){
            person.eat();
        }
    }
}
