package day17;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapTest {
    @Test
    public void test04(){
        Map map=new HashMap();
        map.put("AA",45);
        map.put("BB",56);
        map.put("CC",96);
        map.put("DD",85);
        map.put("GG",75);
        map.put("YY",45);

        Object aa = map.get("AA");
        System.out.println(aa);
        Object hh = map.get("HH");
        System.out.println(hh);// 没有的话就返回null
        System.out.println(map.size());

        // 判断是否存在某个key值
        System.out.println(map.containsKey("YY"));
        System.out.println(map.containsValue(45));

        System.out.println(map.isEmpty());

        Map map1=new HashMap();
        map1.put("AA",45);
        map1.put("BB",56);
        map1.put("CC",96);
        map1.put("DD",85);
        map1.put("GG",751);
        map1.put("YY",45);
        System.out.println(map.equals(map1));
    }
    @Test
    public void test03(){
        Map map=new HashMap();
        map.put("aa",56);
        map.put("bb",96);
        map.put("cc",86);
        map.put("dd",96);
        map.put("hh",86);


        //System.out.println(map);
        Object aa = map.remove("aa");
       // System.out.println(aa);
        //System.out.println(map);

        Map map1=new HashMap();
        map1.put("12","AA");
        map1.put("14","gg");
        map1.put("15","bb");
        map1.put("16","hh");
        map1.put("aa",56);

        System.out.println(map1);
        System.out.println(map);
        map.putAll(map1);
        System.out.println("=================");
        System.out.println(map);
       // map.clear();
        System.out.println(map);
        System.out.println(map.isEmpty());

    }
    @Test
    public void test02(){
        // 通过hashcode()计算出哈希值,然后哈希值通过某种算法计算出在
        // hashMap底层数组的索引位置,判断该索引位置上是否有元素
        // 如果没有元素 则直接添加,
        // 如果有元素b(或以链表结构存储的多个元素,)则比较哈希值是否相等
        // 如果不相等 则添加成功
        // 如果不相等,则调用元素a所在类的equals方法
        // 如果equals方法返回true 则添加不成功,并把旧的额值改为新的值
        // 如果equal是 返回false 则添加成功
        Map map=new HashMap();
        // 需要Cat实现hashCode()和equals()方法
        map.put(new Cat("李白",12),12);
        map.put(new Cat("赵四",56),56);
        map.put(new Cat("李白",12),89);
        map.put(new Cat("杜甫",65),69);

        System.out.println(map);


    }

    @Test
    public void test01(){
        Map map=new HashMap();
        map.put("AA",12);
        map.put("12","AA");
        map.put("13","AA");
        map.put("bb",89);
        System.out.println(map);
        map.put("AA",963);
        System.out.println(map);
    }
}
class Cat{
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return age == cat.age &&
                Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}