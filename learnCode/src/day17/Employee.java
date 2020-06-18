package day17;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private MyDate myDate;

    public Employee(String name, int age, MyDate myDate) {
        this.name = name;
        this.age = age;
        this.myDate = myDate;
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

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", myDate=" + myDate +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
class MyDate{
    private String year;
    private String month;
    private String day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public MyDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }


}
class Test{
    public static void main(String[] args) {
        MyDate myDate=new MyDate("2020","3","23");
        MyDate myDate1=new MyDate("2021","4","23");
        MyDate myDate2=new MyDate("2022","5","24");
        MyDate myDate3=new MyDate("2023","6","25");
        MyDate myDate4=new MyDate("2024","7","26");
        MyDate myDate5=new MyDate("2020","5","23");

        Employee employee=new Employee("李白",23,myDate);
        Employee employee5=new Employee("李白22",23,myDate5);
        Employee employee1=new Employee("李贺",24,myDate1);
        Employee employee2=new Employee("白居易",26,myDate2);
        Employee employee3=new Employee("杜甫",29,myDate3);
        Employee employee4=new Employee("陶渊明",30,myDate4);

        TreeSet<Employee> treeSet = new TreeSet<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int i = o1.getMyDate().getYear().compareTo(o2.getMyDate().getYear());
                int i1 = o1.getMyDate().getMonth().compareTo(o2.getMyDate().getMonth());
                int i2 = o1.getMyDate().getDay().compareTo(o2.getMyDate().getDay());
                if (i == 0 && i1 == 0 && i2 == 0) {
                    return 0;
                } else if (i == -1 || i1 == -1 || i2 == -1) {
                    return -1;
                } else {
                    return 1;
                }


            }

        });
        treeSet.add(employee);
        treeSet.add(employee1);
        treeSet.add(employee2);
        treeSet.add(employee3);
        treeSet.add(employee4);
        treeSet.add(employee5);
        //System.out.println(treeSet);
        Iterator<Employee> iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}