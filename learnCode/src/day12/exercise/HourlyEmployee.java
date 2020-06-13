package day12.exercise;

public class HourlyEmployee extends Employee {
  private double wage;
  private double hour;

    public HourlyEmployee() {
    }

    public HourlyEmployee(String name, String number, MyDate myDate, double wage, double hour) {
        super(name, number, myDate);
        this.wage = wage;
        this.hour = hour;
    }

    public String toString(){
        return super.toString()+" 工资"+earnings();
    }
    @Override
    public double earnings() {
        return wage*hour;
    }
}
