package day12.exercise;

class AbstractTest {
    public static void main(String[] args) {
         //Person person1=new Person(); 编译报错
        Person person=new Chinese();
        System.out.println(person.getName());
        person.speak();
        person.show();

    }
}

abstract class Person<fianl> {
    private String name="习大大";
    private int age;

    public Person() {
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public abstract void speak();// 拥有一个或多个抽象方法的类一定是抽象类
    public abstract void show();
    //public abstract  final void say(); final和abstract不能共存
    // public abstract static void say() ; static 和abstract 不能共存
    // private abstract  void say() ;private 和abstract 不能共存
}

class Chinese extends Person{
    private String name="李克强";
    public Chinese() {

    }

    public Chinese(String name, int age) {
        super(name, age);
    }

    @Override
    public void speak() {
        System.out.println("中国人说中国话");
    }

    @Override
    public void show() {
        System.out.println("李白");
    }
}

abstract class USA extends Person{
    public void speak(){
        System.out.println("美国");
    }
}