package day17;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest01 {
    @Test
    public void test01(){
        TreeSet set=new TreeSet();
        set.add(12);
        set.add(56);
        set.add(986);
        set.add(69);
        System.out.println(set);

    }
    @Test
    public void test02(){
        TreeSet treeSet=new TreeSet();
        treeSet.add("李白");
        treeSet.add("李贺");
        treeSet.add("李白1");
        System.out.println(treeSet);
    }
    @Test
    public void test03(){
        TreeSet treeSet=new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Dog && o2 instanceof Dog){
                    if(((Dog) o1).getAge()==((Dog) o2).getAge()){
                        return ((Dog) o1).getName().compareTo(((Dog) o2).getName());
                    }
                    return -(((Dog) o1).getAge()-((Dog) o2).getAge());
                }
                return 0;
            }
        });
        treeSet.add(new Dog("李白",12));
        treeSet.add(new Dog("李白1",56));
        treeSet.add(new Dog("李贺",16));
        treeSet.add(new Dog("李白",12));
        System.out.println(treeSet);

    }

    @Test
    public void test04(){
        TreeSet treeSet=new TreeSet();
        treeSet.add(new Dog("李白",12));
        treeSet.add(new Dog("李白1",56));
        treeSet.add(new Dog("李贺",16));
        treeSet.add(new Dog("李白",12));
        System.out.println(treeSet);
    }
}
class Dog implements Comparable{
    private String name;
    private  int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof  Dog){

            if(this.age==((Dog) o).age){
                return this.name.compareTo(((Dog) o).name);
            }
            return this.age-((Dog) o).age;

        }
        return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}