package day22.test;

public class PersonTest {
    public static void main(String[] args) {
        // 匿名的类对象 相对于声明了一个匿名的类实现了Person 并且重写了say()方法
        Person person = new Person(){
            public void say(){
                System.out.println("你好");
            }
            public void eat(){
                System.out.println("吃饭");
            }
        };
        person.say();
        person.eat();

    }
}
