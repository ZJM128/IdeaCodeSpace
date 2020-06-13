package day11.work;

public class StaticTest01 {
    public static void main(String[] args) {
        System.out.println(Chinese.nation);
        Chinese chinese1=new Chinese();
        chinese1.setName("卢本伟");
         Chinese chinese=new Chinese();
        chinese.setName("李白");
         chinese.setNation("中国人牛逼");
        System.out.println(chinese.getNation());
        System.out.println(chinese1.getNation());
        Chinese.show();
        System.out.println(chinese);
        System.out.println(chinese1);

    }
}
class Chinese{
    private String name;
    static String nation="中国人";

    public Chinese() {
    }

    public Chinese(String name, String nation) {
        this.name = name;
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public static void show(){
        //this
        //super.clone()
        System.out.println("我是个静态方法");
    }
    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' + " "+nation+
                '}';
    }
}