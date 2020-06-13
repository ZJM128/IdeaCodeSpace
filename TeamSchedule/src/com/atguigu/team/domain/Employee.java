package com.atguigu.team.domain;

public class Employee {
    private int id;
    private String name;
    private int age;
    private double sayaly;

    public Employee(int id, String name, int age, double sayaly) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sayaly = sayaly;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getSayaly() {
        return sayaly;
    }

    public void setSayaly(double sayaly) {
        this.sayaly = sayaly;
    }

    public String  getDetail(){
        return id+"\t"+name+"\t"+age+"\t"+sayaly;
    }

    @Override
    public String toString() {
        return getDetail();
    }
}
