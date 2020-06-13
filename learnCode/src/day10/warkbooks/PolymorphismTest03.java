package day10.warkbooks;

public class PolymorphismTest03 {
    public static void main(String[] args) {
        Person[]people=new Person[3];
        people[0]=new Person();// 本态
        people[1]=new Man();
        people[2]=new Woman();
        for (Person person : people) {
            person.sleep();
            person.eat();
            if(person instanceof Man){
                Man man=(Man)person;
                man.smoking();
            }

            if(person instanceof Woman){
                Woman woman=(Woman)person;
                woman.shopping();
            }
        }
    }
}
