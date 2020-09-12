package day24.factory.simplefactory;
/*
*@Description:简单工厂模式
*@author:zhijm
*@Date:2020/6/24 11:00
*/
public class SimpleFactory {

    public Fruit getFruit(String name){
        if("苹果".equals(name))
         return new BigApple();
        else if("香蕉".equals(name))
            return new Banana();
        else
            return null;
    }
}
