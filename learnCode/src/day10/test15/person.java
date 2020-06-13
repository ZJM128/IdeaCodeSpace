package day10.test15;

public class person {

    int age=10;

    public void eat(){
        System.out.println("吃饭");
    }

    public void toilet(){
        System.out.println("上洗手间");
    }

    public void show(person person){
        person.eat();
    }
    public void print(){
        System.out.println(this.age);
    }
}
