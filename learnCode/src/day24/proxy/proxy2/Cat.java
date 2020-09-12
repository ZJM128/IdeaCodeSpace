package day24.proxy.proxy2;

public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("猫吃鱼<。)#)))≦");
    }

    @Override
    public void sleep() {
        System.out.println("猫睡觉(～﹃～)~zZ");
    }

    @Override
    public String getName() {
        return "小猫猫";
    }
}
