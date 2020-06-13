package com.atguigu.test02.bean;

public class TestCircle {
    public static void main(String []args){
        Circle circle=new Circle(2);
        System.out.println("半径是"+circle.getR());
        circle.showArea();
        circle.showPerimeter();
        circle.setR(3);
        System.out.println("半径是"+circle.getR());
        circle.showArea();
        circle.showPerimeter();
    }
}
