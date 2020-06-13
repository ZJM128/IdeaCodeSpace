package day10.exer;

import org.junit.Test;

public class EmployeeTest {
    @Test
    public void test01(){
        GeneralEmployee generalEmployee=new GeneralEmployee();
        generalEmployee.dailyWage=300;
        generalEmployee.workDay=30;
        generalEmployee.salaryLevel=2;
        generalEmployee.printSalary();

        Manager manager=new Manager();
        manager.dailyWage=500;
        manager.workDay=20;
        manager.salaryLevel=4;
        manager.printSalary();
    }

    public static void main(String[] args) {
        add(1);
    }
    public static void add(int i){
        System.out.println("一个参数的");
    }
    public  static void add(int...a){
        System.out.println("可变");
    }

}
