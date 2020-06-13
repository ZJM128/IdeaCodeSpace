package day11.work;

public class Test14 {
    public static void main(String[] args) {
        Father12 f = new Father12();
        Son12 s = new Son12();
        System.out.println(f.getInfo());// atguigu
        System.out.println(s.getInfo());// 尚硅谷
        s.test();// 尚硅谷 atguigu
        System.out.println("-----------------");
        s.setInfo("大硅谷");
        System.out.println(f.getInfo());// atguigu
        System.out.println(s.getInfo());// 大硅谷
        s.test();// 大硅谷 atguigu
    }
}
class Father12{
    private String info = "atguigu";
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }
}
class Son12 extends Father12{
    private String info = "尚硅谷";
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }
    public void test(){
        System.out.println(this.getInfo());
        System.out.println(super.getInfo());// super.getInfo()访问的是重父类继承下来的那个值 atguigu
    }
}