package day11.work;

public class Test12 {
    public static void main(String[] args) {
        Father9 f = new Father9();
        Son1 s = new Son1();
        System.out.println(f.getInfo());//atguigu
        System.out.println(s.getInfo());// atguigu
        s.setInfo("尚硅谷");
        System.out.println(f.getInfo());//atguigu
        System.out.println(s.getInfo());//尚硅谷
    }
}
class Father9{
    private String info = "atguigu";
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }
}
class Son1 extends Father9{

}