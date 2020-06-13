package day06;

public class Girl {
    private String name;

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void marry(Boy boy){
        System.out.println("i am "+getName()+",I want to marry"+ boy.getName());
        boy.shout(this);
    }
}
