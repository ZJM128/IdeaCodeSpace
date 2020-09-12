package day09;



public class CircleTest {

    public void test01(){
        Cylinder cylinder = new Cylinder();
        cylinder.setLength(3.6);
        cylinder.setRadius(3);
        System.out.println(cylinder.findVolume());
    }
}
