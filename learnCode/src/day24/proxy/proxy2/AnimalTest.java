package day24.proxy.proxy2;

public class AnimalTest {
    public static void main(String[] args) {
        Dog dog=new Dog();
        Cat cat=new Cat();
        ProxyObject proxyObject=new ProxyObject();
        Animal animal = (Animal) proxyObject.getProxyObject(dog);
        Animal animal1 = (Animal) proxyObject.getProxyObject(cat);
        System.out.println(animal.getName());
        animal.eat();
        animal.sleep();

        System.out.println("------------------------");
        System.out.println(animal1.getName());
        animal1.eat();
        animal1.sleep();

    }
}
