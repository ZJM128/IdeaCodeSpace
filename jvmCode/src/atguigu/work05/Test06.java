package atguigu.work05;

public class Test06 {
    public static void main(String[] args) {
        Triangle triangle=new Triangle();
        triangle.a=12;
        triangle.b=13;
        triangle.c=12;

        System.out.println(triangle.isRightTriangle()?"是":"否");
        System.out.println(triangle.isLsoscelesTriangle()?"是":"否");
        System.out.println(triangle.isEquilateralTriangle()?"是":"否");

        System.out.println(triangle.getArea());
        System.out.println(triangle.getLength());
    }
}
