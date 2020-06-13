package day12;

class HoulyEmployee extends Employee {

    private  double wage;
    private double hour;

    public HoulyEmployee() {
    }

    public HoulyEmployee(String name, double number, MyDate birthday, double wage, double hour) {
        super(name, number, birthday);
        this.wage = wage;
        this.hour = hour;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    @Override
    public double earnings() {
        return wage*hour;
    }

    @Override
    public String toString() {
        return super.toString()+" "+"小时"+hour+" "+"工钱"+wage;
    }
}
