package day16.review01;


import java.util.ArrayList;
import java.util.Collection;

/**
 * 有序,可重复
 * 集合里面装的都是对象类型
 */
public class CollectionTest {
    public static void main(String[] args) {
        Collection collection =new ArrayList();

        collection.add(12);
        collection.add("123");
        collection.add(new Animal(23));
        collection.add("123");
        collection.add(12);//自动装箱
        collection.add(String.valueOf(23));
        //collection.add(new String("23"));
        System.out.println(collection);


        // 有几个有效的元素
       /// System.out.println(collection.size());

        // 清空
      // collection.clear();
        //System.out.println(collection);
        //System.out.println(collection.size());
        //boolean empty = collection.isEmpty();
        ///System.out.println(empty);
       // Collection list = Arrays.asList(1, 9, 4, 5, 6, 3);
       // System.out.println(list);

        //boolean contains = collection.contains("123");
        //System.out.println(contains);
        //boolean contains = collection.contains(new Animal(23));// 调用的是Object的equals方法,比较的是地址值  需要重写equals方法才能进行内容的比较
       // System.out.println("对象的"+contains);
        // String重写了equals 的方法 比较的是内容是否相同
        //System.out.println("string :"+collection.contains(new String("23")));

        //boolean b = collection.containsAll(list);
        //System.out.println(b);
        Collection collection1=new ArrayList();
        collection1.add(12);
        collection1.add("123");
        collection1.add(new String("李白"));
        collection1.add(new Animal(33));

       // collection.removeAll(collection1);
        //collection.remove(12);
       // System.out.println(collection);
        collection.retainAll(collection1);
        System.out.println(collection);
        // 集合转数组
        Object[] objects = collection.toArray();
        for (Object object : objects) {
            System.out.println(object);
        }

    }
}
class Animal{
    private int age;

    public Animal(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age;
    }

}