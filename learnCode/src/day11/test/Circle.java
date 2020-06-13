package day11.test;

public class Circle extends GeometricObject {
    private double radius;

    public Circle() {
        color="white";
        weight=1.0;
        radius=1.0;
    }

    public Circle(double radius) {
        color="white";
        weight=1.0;
        this.radius = radius;
    }

    public Circle(String color, double weight, double radius) {
        super(color, weight);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double findArea(){
        return Math.PI*radius*radius;
    }
    public boolean equals(Circle circle){
        if(this==circle){
            return true;
        }
        if(circle.color.equals(this.color) || circle.radius==this.radius){
            return true;
        }else{
            return false;
        }
    }
    public String toString(){
        return "半径为:"+radius+" 颜色为"+color+" 的圆形";
    }
}
