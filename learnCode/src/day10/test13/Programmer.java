package day10.test13;

public class Programmer extends Employee {
    private String language="java";

    public Programmer() {
    }

    public Programmer(String language) {
        this.language = language;
    }

    public Programmer(String id, String name, int age, double salary) {
        super(id, name, age, salary);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getInfo(){
        return super.getInfo()+"\t"+language;
    }
}
