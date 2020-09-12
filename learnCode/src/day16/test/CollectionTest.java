package day16.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class CollectionTest {
    public static void main(String[] args) {
        Collection collection = new ArrayList();
        // 添加元素到集合中
        collection.add("BB");
        collection.add(new String("AA"));
        collection.add(new Person1("李白",16));

        collection.add(123);//自动装箱

        // 查看集合中的元素的个数
        //System.out.println(collection.size());
        // 清空集合
       // collection.clear();
        //System.out.println(collection.size());
       // boolean empty = collection.isEmpty();
        //System.out.println(empty);

        Collection list = Arrays.asList(1, 2, 5, 6);
        collection.addAll(list);
        //System.out.println(collection);

        // contains(object o)
        boolean aa = collection.contains(new String("AA"));
        //System.out.println(aa);

        boolean flag = collection.contains(new Person1("李白", 16));
        //System.out.println(flag);
        Collection collection1=new ArrayList();
        Collection list1 = Arrays.asList("AA", "BB1");
        collection1.addAll(list1);
        boolean b = collection.containsAll(collection1);
        //System.out.println(b);

        System.out.println(collection);
         collection.remove(new String("AA"));
        System.out.println(collection);
        //collection.remove(new Person("李白", 16));
        //collection.removeAll(list);
        //System.out.println(collection);
        // 取交集
       // collection.retainAll(list);
       // System.out.println(collection);

        Object[] objects = collection.toArray();
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    @Test
    public void test01(){
        System.out.println(122);
    }

}
class Person1{
    private String name;
    private  int age;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person1 person1 = (Person1) o;
        return age == person1.age &&
                Objects.equals(name, person1.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}