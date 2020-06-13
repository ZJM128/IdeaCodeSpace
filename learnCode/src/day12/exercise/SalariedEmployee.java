package day12.exercise;

public class SalariedEmployee extends Employee {

   private double monthlySalary;

    public SalariedEmployee() {
    }

    public SalariedEmployee(String name, String number, MyDate myDate, double monthlySalary) {
        super(name, number, myDate);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    public String toString(){
        return super.toString()+" 工资"+monthlySalary;
    }
    @Override
    public double earnings() {
        return monthlySalary;
    }
}
