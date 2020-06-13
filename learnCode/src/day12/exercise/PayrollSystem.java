package day12.exercise;

import java.util.Scanner;

public class PayrollSystem {
    public static void main(String[] args) {
        Employee[] employees=new Employee[3];
        MyDate myDate=new MyDate(2013,12,31);
        MyDate myDate1=new MyDate(1965,1,18);
        MyDate myDate2=new MyDate(1995,1,30);
        employees[0]=new SalariedEmployee("李白","123",myDate,5000);
        employees[1]=new SalariedEmployee("白居易","124",myDate1,6000);
        employees[2]=new HourlyEmployee("李贺","125",myDate2,2000,6);
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("请输入月份");
        Scanner scanner=new Scanner(System.in);
        int month=scanner.nextInt();
        for (Employee employee : employees) {
            System.out.println("生日前的公资"+employee.earnings());
            if(employee.getMyDate().getMonth()==month){
                System.out.println("生日的公资"+(employee.earnings()+100));
            }
        }
    }
}
