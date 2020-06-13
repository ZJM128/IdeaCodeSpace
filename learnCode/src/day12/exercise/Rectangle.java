package day12.exercise;

public class Rectangle implements Sortable {

    private double length;
    private double width;

    public Rectangle() {
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double area(){
        return length*width;
    }
    @Override
    public int compare(Sortable s) {
        if(s==this){
            return 0;
        }
        if(s instanceof  Rectangle){
            if(((Rectangle) s).area()==this.area()){
                return 0;
            }else if(this.area()>((Rectangle) s).area()){
                return 1;
            }else{
                return -1;
            }
        }
        return -2;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}
