package day12;

/*
*@Description:将Frock类声明为抽象类，在类中声明抽象方法calcArea方法，用来计算衣服的布料面积。
通过编写代码来验证抽象类中是否可包含属性、具体方法和构造器。
编写Shirt类继承Frock类，实现 calcArea方法，用来计算衬衣所需的布料面积（尺寸*1.3）。
在TestShirt类的main方法中：

*@author:zhijm
*@Date:2020/6/8 11:27
*/
abstract public class Fork {
    private double length;
    private String color;
    public Fork(){}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Fork(double length, String color) {
        this.length = length;
        this.color = color;
    }

    public Fork(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public abstract double calcArea();
}

class  shirt extends Fork implements Clothing{
    private double width;
    public shirt() {}
    public shirt(double length, double width) {
        super(length);
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double calcArea(){
        return width*super.getLength();
    }

    @Override
    public String getColor() {
        return "blue";
    }

    @Override
    public String getDetails() {
        return null;
    }


}

/**
 * 试着创建Frock对象，确认是否允许？
 * 使用本态引用创建Shirt对象，并调用calcArea方法，打印计算结果。
 * 使用Frock 多态引用创建Shirt对象，并调用calcArea方法，打印计算结果。
 */
class TestShirt{
    public static void main(String[] args) {
        shirt shirt=new shirt(12,36);
        System.out.println(shirt.calcArea());
        Fork fork=new shirt(1,6);
        System.out.println(fork.calcArea());

        Clothing clothing=new shirt(23,63);
        System.out.println(clothing.calcArea());
    }
}