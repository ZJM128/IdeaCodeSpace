package day16.test;

public class Person {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
class Student extends Person{

}
class Test1{
    public static void main(String[] args) {
        //Person person=new Person();

        Person person=new Student();
        person.setAge(12);
        System.out.println(person.getAge());
    }

}