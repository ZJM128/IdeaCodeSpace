package day26.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Dog dog=new Dog();
        Animal animal = (Animal) ProxyObject.getProxy(dog);
        animal.eat();
        animal.sleep();
        Animal animal1 = (Animal) ProxyObject.getProxy(new Cat());
        animal1.eat();
        animal1.sleep();
    }
}
interface Animal{
    void eat();
    void sleep();
}
class Dog implements Animal{

    @Override
    public void eat() {
        System.out.println("U·ェ·U吃ε==3");
    }

    @Override
    public void sleep() {
        System.out.println("U·ェ·U😪");
    }
}
class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("o(=•ェ•=)m吃🐠");
    }

    @Override
    public void sleep() {
        System.out.println("o(=•ェ•=)m(～﹃～)~zZ");
    }
}

class ProxyObject{

    public static Object getProxy(Object o){
        MyInvocationHandler myInvocationHandler=new MyInvocationHandler(o);
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),myInvocationHandler);
    }
}
class MyInvocationHandler implements InvocationHandler{
    private Object object;
    public MyInvocationHandler(Object o){
        object=o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("===============================================");
        System.out.println("生物进化~~~");
        method.invoke(object, args);
        System.out.println("生物进化完毕");
        System.out.println("===============================================");
        return null;
    }
}