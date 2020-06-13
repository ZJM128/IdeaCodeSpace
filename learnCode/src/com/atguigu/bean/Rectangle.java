package com.atguigu.bean;

public class Rectangle {
    private double length;
    private  double width;

    public Rectangle(){}
    public void setLength(double length){
        this.length=length;
    }

    public void setWidth(double width){
        this.width=width;
    }

    public double getLength(){
        return length;
    }
    public double getWidth(){
        return width;
    }

    public double getArea(){
        return length*width;
    }

    public double getPerimeter(){
        return (length+width)*2;
    }

    public String getInfo(){
        return "长:"+length+
                " 宽:"+width+
                "面积:" +getArea()
                 +"周长"+getPerimeter();
    }
}
