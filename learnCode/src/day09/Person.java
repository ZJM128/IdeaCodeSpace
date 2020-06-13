package day09;

public class Person {

    private String name;
    private String Id;
    private int age;

    public Person(){}

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void eat(){
        System.out.println("吃饭");
    }

}
