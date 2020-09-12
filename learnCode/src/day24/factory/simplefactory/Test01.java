package day24.factory.simplefactory;
/*
*@Description:简单工厂违反到了开闭原则,可以修改 ,但是新增的时候不能修改原来的功能
*@author:zhijm
*@Date:2020/6/24 10:48
*/
public class Test01 {
    public static void main(String[] args) {
        // 开闭原则
        SimpleFactory simpleFactory=new SimpleFactory();
        Fruit fruit = simpleFactory.getFruit("苹果");
        fruit.eat();
        Fruit fruit1 = simpleFactory.getFruit("香蕉");
        fruit1.eat();
    }
}
