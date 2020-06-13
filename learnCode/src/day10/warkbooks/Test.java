package day10.warkbooks;

public class Test {

    @org.junit.Test
    public void test01(){
        Circle circle=new Circle("blue",5.6,9.6);
        MyRectangle myRectangle=new MyRectangle("red",5.6,2.6,9.3);
        GeometricObject[]geometricObjects=new GeometricObject[2];
        geometricObjects[0]=circle;
        geometricObjects[1]=myRectangle;
        System.out.println(equalsArea(geometricObjects)?"相等":"不相等");
        displayGeometricObject(circle);
        displayGeometricObject(myRectangle);

    }

    public boolean equalsArea(GeometricObject[] geometricObject){
        if(geometricObject[0].findArea()==geometricObject[1].findArea()){
            return true;
        }
        return false;
    }

    public void displayGeometricObject(GeometricObject geometricObject){
        System.out.println("面积为"+geometricObject.findArea());
    }
}
