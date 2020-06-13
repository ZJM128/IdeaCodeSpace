package day11.work;

public class ObjectTest {
    public static void main(String[] args) {
        // tostring
        /*Person person=new Person();
        System.out.println(person);//day11.work.Person@14ae5a5

        Person person1=new Person("12",18);
        System.out.println(person1);*/

        // equals
        /*int a=45;
        int b=90;
        if(a==b){
            System.out.println("相等");
        }else{
            System.out.println("不相等");
        }*/

        Person person=new Person("张三",18);
        Person person1=new Person("张三",18);

        System.out.println(person==person1);//false
        System.out.println(person.equals(person1));//false 两者其实是一样的,因为底层都是"=="


    }
}
class  Person{
    private String name;
    private int age;

    public Person() { }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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
    public String toString(){
        return name+" "+age;
    }

    public boolean equals(Object o){
        if(o==this){
            return true;
        }

        if(o instanceof Person){
            if(((Person) o).name.equals(this.name) && ((Person) o).age==this.age){
                return true;
            }
        }
        return false;
    }
}