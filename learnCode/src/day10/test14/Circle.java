package day10.test14;

public class Circle extends Graphic {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea(){
        return Math.PI*radius*radius;
    }
    public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    public String getInfo(){
        return "半径为"+radius+"的圆型"+"面积:"+getArea();
    }
}
