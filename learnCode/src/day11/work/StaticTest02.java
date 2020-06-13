package day11.work;

public class StaticTest02 {
    public static void main(String[] args) {
        person3 person=new person3();
        System.out.println();
        person3  person1=new person3();
        System.out.println();
        person3  person2=new person3();
        person3.numer=122;
        System.out.println(person3.numer);

    }
}
class person3{

    private String name="李白";
    private int age;
    public static   int numer;
    public person3(){
        System.out.println("我是构造方法");
    }
    static{

        System.out.println("我是静态代码块 呀呀呀呀呀");
    }
    static{
        System.out.println("我是2222222静态代码块 呀呀呀呀呀");
    }
    {

        System.out.println("我是代码快2222 呀呀呀");
    }
    {
        System.out.println("我是代码快哦 呀呀呀");
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

    public static int getNumer() {
        return numer;
    }
}