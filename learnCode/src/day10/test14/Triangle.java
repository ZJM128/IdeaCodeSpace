package day10.test14;

public class Triangle extends Graphic {

    private  double length;
    private double height;

    public Triangle(double length, double height) {
        this.length = length;
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea(){
        return length*height/2;
    }
    public double getPerimeter(){
        return (height+length)*2;
    }
    public String getInfo(){
        return "边长为"+length+" 高为:"+height+"的三角型"+"面积:"+getArea();
    }
}
