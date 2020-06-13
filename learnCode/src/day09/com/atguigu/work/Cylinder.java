package day09.com.atguigu.work;

public class Cylinder extends Circle {
    private double length;

    public Cylinder() {
        this.length=1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    public double findVolume(){
        return findArea()*length;
    }
}
