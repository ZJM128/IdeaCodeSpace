package day16.review01;

import java.util.Comparator;
import java.util.TreeSet;

public class Test08 {
    public static void main(String[] args) {

        TreeSet<Student4>treeSet=new TreeSet<>();
        Student4 student=new Student4("A",20,90.0);
        Student4 student1=new Student4("C",22,90.0);
        Student4 student2=new Student4("D",20,99.0);
        Student4 student3=new Student4("B",22,100.0);
        treeSet.add(student);
        treeSet.add(student1);
        treeSet.add(student2);
        treeSet.add(student3);
        System.out.println("按成绩和年龄排名");
        for (Student4 student4 : treeSet) {
            System.out.println(student4);
        }

        System.out.println("按照姓名排序");
        TreeSet<Student4> treeSet1=new TreeSet<>(new Comparator<Student4>() {
            @Override
            public int compare(Student4 o1, Student4 o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Student4 student4 : treeSet) {
            System.out.println(student4);
        }
        for (Student4 student10 : treeSet1) {
            System.out.println(student10);
        }

    }
}
