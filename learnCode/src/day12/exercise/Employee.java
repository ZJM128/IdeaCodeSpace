package day12.exercise;

abstract class Employee {
    private String name;
    private String number;
    private MyDate myDate;

    public Employee() {
    }

    public Employee(String name, String number, MyDate myDate) {
        this.name = name;
        this.number = number;
        this.myDate = myDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", myDate=" + myDate +
                '}';
    }
    public abstract double earnings();
}
