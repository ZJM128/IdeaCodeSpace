package com.atguigu.test;

import com.atguigu.bean.Rectangle;

public class Test07 {

    public static void main(String []args){
        Rectangle rectangle=new Rectangle();

        rectangle.setLength(2.3);
        rectangle.setWidth(1.6);
        System.out.println("面积"+rectangle.getArea());
        System.out.println("周长"+rectangle.getPerimeter());
        rectangle.setLength(4.6);
        rectangle.setWidth(3.2);
        System.out.println(rectangle.getInfo());
    }
}
