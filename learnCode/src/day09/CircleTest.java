package day09;

import org.junit.Test;

public class CircleTest {
    @Test
    public void test01(){
        Cylinder cylinder = new Cylinder();
        cylinder.setLength(3.6);
        cylinder.setRadius(3);
        System.out.println(cylinder.findVolume());
    }
}
