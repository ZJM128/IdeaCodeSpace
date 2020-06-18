package day18;

public class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private MyDate bithday;

    public Employee(String name, int age, MyDate bithday) {
        this.name = name;
        this.age = age;
        this.bithday = bithday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBithday() {
        return bithday;
    }

    public void setBithday(MyDate bithday) {
        this.bithday = bithday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bithday=" + bithday +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
