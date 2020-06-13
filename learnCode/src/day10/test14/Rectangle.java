package day10.test14;

public class Rectangle extends Graphic {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea(){
        return width*height;
    }
    public double getPerimeter(){
        return (height+width)*2;
    }
    public String getInfo(){
        return "长为"+width+" 高为:"+height+"的矩型"+"面积:"+getArea();
    }
}
