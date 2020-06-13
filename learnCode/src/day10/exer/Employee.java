package day10.exer;
/*
*@Description:：通过继承实现员工工资核算打印功能。有员工类，普通员工类和部门经理类，
普通员工工资=单日工资*天数*等级（1）；部门经理工资=1000+单日工资*天数*等级（1.2）。
a)	员工属性（单日工资，工作天数，工资等级），员工方法（打印工资）
b)	普遍员工及部门经理都是员工子类，需要重写打印工资方法。
定义并初始化普通员工对象，调用打印工资方法输入工资，定义并初始化部门经理对象，
调用打印工资方法输入工资
*@author:zhijm
*@Date:2020/6/5 8:26
*/
public class Employee {
     double dailyWage;
    int workDay=12;
    int salaryLevel;

    public double getDailyWage() {
        return dailyWage;
    }

    public int getWorkDay() {
        return workDay;
    }

    public double getSalaryLevel() {
        return salaryLevel;
    }

    public void setDailyWage(double dailyWage) {
        this.dailyWage = dailyWage;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public void setSalaryLevel(int salaryLevel) {
        this.salaryLevel = salaryLevel;
    }
    public void printSalary(){
        System.out.println(dailyWage);
    }
}
