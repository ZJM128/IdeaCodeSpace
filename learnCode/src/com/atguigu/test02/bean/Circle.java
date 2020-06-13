package com.atguigu.test02.bean;


public class Circle {
    private double r;
    public Circle(){}
    public Circle(double r){
        this.r=r;
    }
    public void setR(int r){
        this.r=r;
    }

    public double getR(){
        return r;
    }

    public void showArea(){
        System.out.println("圆的面积是:"+(Math.PI*r*r));
    }

    public void showPerimeter(){
        System.out.println("圆的周长是:"+(2*Math.PI*r));
    }
}
