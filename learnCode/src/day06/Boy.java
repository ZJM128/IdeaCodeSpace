package day06;

public class Boy {
    private String name;
    private int age;

    public void setName(String name){
        this.name=name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

    public void marry(Girl girl){
        System.out.println("I am "+getName()+" and "+getAge()+"age want to marry "+girl.getName());
    }

    public void shout(Girl girl){
        System.out.println("I want to marry you "+girl.getName() +" as well");
    }
}
