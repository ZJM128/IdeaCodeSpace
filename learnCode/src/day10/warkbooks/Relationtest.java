package day10.warkbooks;

import day09.Computer;

public class Relationtest {
    public static void main(String[] args) {
        Computer computer=new Computer("i7","32G","500G",9635);
        Teacher teacher=new Teacher("李四",23,computer);
        teacher.show();
    }
}
class Teacher{
    private String name;
    private int age;
    private Computer computer;

    public Teacher() { }

    public Teacher(String name, int age, Computer computer) {
        this.name = name;
        this.age = age;
        this.computer = computer;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
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

    public void show(){
        System.out.println(name+" "+age+" "+computer.getDetail() );
    }
}