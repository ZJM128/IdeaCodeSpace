package day10.exer;

public class GeneralEmployee extends Employee{
    int workDay=26;
    public void printSalary(){
        System.out.println(workDay);
        double salary=dailyWage*workDay*salaryLevel;
        System.out.println("普通员:您的工资是:"+salary);

    }
}
