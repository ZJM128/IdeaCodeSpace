package day10.test14;

public class Teat14 {

    public static boolean equalsArea(Graphic graphics1,Graphic graphic2){
        if(graphics1.getArea()==graphic2.getArea()){
            return true;
        }
        return false;
    }

    public static Graphic maxArea(Graphic graphic,Graphic graphic1){
        if(graphic.getArea()>graphic1.getArea()){
            return graphic;
        }
        return graphic1;
    }

    public static void main(String[] args) {

        Circle circle=new Circle(13);

        Rectangle rectangle=new Rectangle(5,6);

        Triangle triangle=new Triangle(4,2.5);

        System.out.println(equalsArea(circle, rectangle)?"相同":"不相同");
        System.out.println(maxArea(circle, triangle).getInfo());
    }
}
