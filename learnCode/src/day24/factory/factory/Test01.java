package day24.factory.factory;
/*
*@Description:工厂设计模式
* 需要定义一个工厂的接口,这个接口规范了其他工厂类应该具备的功能.
* 定义一个物品的接口,这个接口也规范了其他物品的应该具备什么样的功能
*@author:zhijm
*@Date:2020/6/24 10:59
*/
public class Test01 {
    public static void main(String[] args) {
//        Fruit fruit = new AppleFactory().getFruit();
//        fruit.eat();
//        Fruit fruit1 = new BananaFactory().getFruit();
//        fruit1.eat();
        Factory factory = new AppleFactory();
        Fruit fruit= factory.getFruit();
        fruit.eat();
    }
}
