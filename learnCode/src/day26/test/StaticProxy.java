package day26.test;

public class StaticProxy {
    public static void main(String[] args) {
        RealerStart realerStart=new RealerStart();
        ProxyClass proxyClass=new ProxyClass();
        ProxyClass.setStart(realerStart);
        proxyClass.sing();
        proxyClass.dance();
    }
}
interface  start{
    void sing();
    void dance();
}
class RealerStart implements start{
    @Override
    public void sing() {
        System.out.println("刘亦菲唱歌");
    }

    @Override
    public void dance() {
        System.out.println("刘亦菲跳舞");
    }
}

class ProxyClass implements start{

    private  static start restart;
    public static void setStart(start start){
        restart=start;
    }

    @Override
    public void sing() {
        System.out.println("唱歌准备");
        restart.sing();
        System.out.println("唱歌结束");
    }

    @Override
    public void dance() {

        System.out.println("跳舞准备");
        restart.dance();
        System.out.println("跳舞结束");
    }
}