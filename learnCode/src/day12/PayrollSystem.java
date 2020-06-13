package day12;

import java.util.Scanner;

public class PayrollSystem {
    public static void main(String[] args) {
        Employee[]employees=new Employee[3];
        MyDate myDate=new MyDate(2012,12,30);
        MyDate myDate1=new MyDate(1992,5,21);
        MyDate myDate2=new MyDate(1998,12,31);
        employees[0]=new SalariedEmployee("李白",1200,myDate,60000);
        employees[1]=new SalariedEmployee("白居易",1300,myDate1,70000);
        employees[2]=new HoulyEmployee("白居易",1300,myDate2,3000,8);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入月份");
        int month=scanner.nextInt();

        for (Employee employee : employees) {
            if(employee.getBirthday().getMonth()==month){
                if(employee instanceof SalariedEmployee){
                    ((SalariedEmployee) employee).setMonthlySalary(((SalariedEmployee)employee).earnings()+100);
                    System.out.println(employee);
                    System.out.println(employee.earnings());
                }
                if(employee instanceof HoulyEmployee){
                    ((HoulyEmployee) employee).setWage(((HoulyEmployee) employee).getWage()+100);
                    System.out.println(employee);
                    System.out.println(employee.earnings());
                }
            }
        }
    }
}
