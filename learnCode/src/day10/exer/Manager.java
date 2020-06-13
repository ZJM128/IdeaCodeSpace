package day10.exer;

public class Manager extends Employee {
    public void printSalary(){
        double salary=1000+dailyWage*workDay*salaryLevel;
        System.out.println("经理:您的工资是:"+salary);
    }
}
