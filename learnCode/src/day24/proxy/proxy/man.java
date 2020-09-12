package day24.proxy.proxy;

public class man implements Person {
    @Override
    public void eat() {
        System.out.println("男人吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("男人睡觉");
    }
}
