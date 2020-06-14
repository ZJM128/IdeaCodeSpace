package day16.review01;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 无序 不可重复
 */
public class SetTest {
    public static void main(String[] args) {
        Set set=new HashSet();
        set.add("12");
        set.add("12");
        set.add(12);
        set.add(12);
        // 两者的hashCode不一样 因为调用的是object 的hashcode方法生成的哈希值 需要重写hashcode方法
        set.add(new Animal1(12));
        set.add(new Animal1(12));
        System.out.println(set);
    }
}
class Animal1{
    private int age;

    public Animal1(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal1 animal = (Animal1) o;
        return age == animal.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return "Animal1{" +
                "age=" + age +
                '}';
    }
}