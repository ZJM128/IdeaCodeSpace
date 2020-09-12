package day24.proxy.proxy;

public class Woman implements Person {
    @Override
    public void eat() {
        System.out.println("女人吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("女人睡觉");
    }
}
