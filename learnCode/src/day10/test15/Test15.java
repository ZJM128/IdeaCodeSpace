package day10.test15;

public class Test15 {
    public static void main(String[] args) {

       /* Man man=new Man();
        Woman woman=new Woman();
        meeting(man);
        meeting(woman);*/

       person person=new Man();
       person.show(person);
        System.out.println(person.age);
       person.print();


    }


    public static void meeting(person person){
        person.eat();
        person.toilet();
        if(person instanceof Man){
            ((Man) person).smoking();
        }

        if(person instanceof Woman){
            ((Woman) person).makeup();
        }
    }
}
