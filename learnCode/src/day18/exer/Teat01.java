package day18.exer;

import java.util.Comparator;
import java.util.TreeSet;

public class Teat01 {
    public static void main(String[] args) {
        TreeSet<Student>treeSet=new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        treeSet.add(new Student("liusan",20,90));
        treeSet.add(new Student("lisi",22,90));
        treeSet.add(new Student("wangwu",20,99));
        treeSet.add(new Student("sanliu",22,100));

       for(Student student:treeSet){
           System.out.println(student);
       }
    }
}
