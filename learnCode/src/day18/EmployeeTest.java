package day18;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class EmployeeTest {
    @Test
    public void test01(){
        MyDate myDate=new MyDate(2020,2,6);
        MyDate myDate1=new MyDate(2021,3,6);
        MyDate myDate2=new MyDate(2022,2,6);
        MyDate myDate3=new MyDate(2023,4,6);
        MyDate myDate4=new MyDate(2024,5,6);
        Employee employee=new Employee("李白",56,myDate);
        Employee employee1=new Employee("白居易",65,myDate1);
        Employee employee2=new Employee("李贺",75,myDate2);
        Employee employee3=new Employee("苏轼",42,myDate3);
        Employee employee4=new Employee("杜甫",23,myDate4);

        TreeSet<Employee> treeSet=new TreeSet<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if(o1.getBithday().getYear().equals(o2.getBithday().getYear())){
                    if(o1.getBithday().getMonth().equals(o2.getBithday().getMonth())){
                        return o1.getBithday().getDay().compareTo(o2.getBithday().getDay());
                    }else{
                        return -1;
                    }
                }else {
                    return -1;
                }
            }
        });

        treeSet.add(employee);
        treeSet.add(employee1);
        treeSet.add(employee2);
        treeSet.add(employee3);
        treeSet.add(employee4);
        Iterator<Employee> iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("=======================");
        for(Employee employee5:treeSet){
            System.out.println(employee);
        }
    }
}
