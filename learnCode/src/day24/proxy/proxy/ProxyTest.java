package day24.proxy.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        man man=new man();
        Person person = (Person) ProxyClass.getProxyClass(man);//此处可以使用被代理对象的任何一个实现的接口接收
        person.eat();
        person.sleep();
        Woman woman=new Woman();

        Person person1 = (Person) ProxyClass.getProxyClass(woman);
        person1.eat();
        person1.sleep();


    }
}
