package day13;
/*
*@Description:1.编写一个Person类，使用Override注解它的toString方法

2.自定义一个名为“MyTiger”的注解类型，它只可以使用在方法上，带一个String类型的value属性，然后在第1题中的Person类上正确使用。
*@author:zhijm
*@Date:2020/6/9 14:42
*/
    public class Person {
    @Override
    public String toString(){
        return "jhh";
    }
    @Mytiger("hello")
    public void show(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
