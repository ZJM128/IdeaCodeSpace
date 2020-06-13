package day12;

public class Equipment {
    private String name;
    private String color;

    public Equipment() {
    }

    public Equipment(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void  say(int a,int b){

        System.out.println(b+a);
    }
    public void say(int a){
        System.out.println(a);
    }

    public static void main(String[] args) {
        Equipment equipment=new Equipment();
        equipment.say(1,6);
        equipment.say(3);
    }
}
