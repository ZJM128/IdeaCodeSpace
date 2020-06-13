package day13.review;

@Deprecated
@MyAnnotationTest(name = "good")
public class Person {
    public static void main(String[] args) {
        Person person=new Person("a",12);
    }
    @Deprecated
    private String name;
    private int ag;


    public Person() {
    }


    public Person(String name, int ag) {
        this.name = name;
        this.ag = ag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAg() {
        return ag;
    }

    public void setAg(int ag) {
        this.ag = ag;
    }

    @Deprecated
    public void sleep(){
        System.out.println("碎觉");
    }
}

class  Student extends Person{
    @Override
    public void sleep(){
        System.out.println("水煎");
    }
}

