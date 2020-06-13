package day11.test;

public class Teat {
    public static void main(String[] args) {
        Circle circle=new Circle("blue",1.3,1.3);
        Circle circle1=new Circle(1.6);
        System.out.println(circle.equals(circle1));
        System.out.println(circle.toString());
        System.out.println(circle1.toString());

        /**
         * 子类没有重写equals方法时,调用的是从父类中继承下来的精确的equals(object ojb)
         */
        GeometricObject geometricObject =new Circle("white",1.3,1.3);
        GeometricObject geometricObject1=new Circle(1.3);
        System.out.println(geometricObject.equals(geometricObject1));
        System.out.println(geometricObject);
        System.out.println(geometricObject1.toString());
    }
}
