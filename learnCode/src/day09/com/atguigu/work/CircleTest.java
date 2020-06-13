package day09.com.atguigu.work;

import org.junit.Test;

public class CircleTest {
    @Test
    public void test(){
        Cylinder cylinder=new Cylinder();
        System.out.println("体积"+cylinder.findVolume());
        cylinder.setLength(2.3);
        cylinder.setRadius(36);
        System.out.println("体积"+cylinder.findVolume());
    }

}
