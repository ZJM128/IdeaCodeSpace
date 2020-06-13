package day16.morning;

class Test{
    public static void main(String[] args) {
        Person person=new Son();
        System.out.println(person.name);
        System.out.println(person.getAge());

    }
}
 class Person {
     String name="zhijm";
     private int age=12;

     public int getAge() {
         return age;
     }
 }

class Son extends Person{
    private int age=103;

     String name="李白";


}