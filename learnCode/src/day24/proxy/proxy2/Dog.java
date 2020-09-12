package day24.proxy.proxy2;

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("狗吃骨头ε==3");
    }

    @Override
    public void sleep() {
        System.out.println("狗碎觉😪");
    }

    @Override
    public String getName() {
        return "小狗狗";
    }
}
