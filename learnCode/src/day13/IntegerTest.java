package day13;

import org.junit.Test;

/**
 * 编写程序，main方法中接收两个数字字符串参数；
 * 将第一个字符串直接转为Integer对象；
 * 将第二个字符串解析int整数，然后用此整数构建Integer对象；
 * 使用人工拆箱，获得上述两个对象int整数值，求和打印出来；
 * 使用自动拆箱，计算两数之积并打印出来
 */
public class IntegerTest {
    public static void main(String[] args) {
//        Integer integer=63;
//        Integer integer1=63;
//        System.out.println(integer==integer1);
//        Integer integer2=963;
//        Integer integer3=963;
//        System.out.println(integer2==integer3);
        Integer integer=new Integer(args[0]);
        int i=Integer.parseInt(args[1]);
        Integer integer1=new Integer(i);
        Integer integer2 = Integer.valueOf(i);

        int i1 = integer.intValue();
        int i2 = integer1.intValue();
        System.out.println("和为:"+(i1+i2));

        int i3=integer;
        int i4=integer2;
        System.out.println("乘积为:"+(i3*i4));

    }
    @Test
    public void test01(){
        String str="12";
        Integer integer=new Integer(str);
        System.out.println(integer);

        Integer integer1 = Integer.valueOf(str);
        System.out.println(integer1);

        String str1="145";

        int i = Integer.parseInt(str1);
        //Integer integer2=new Integer(i);
        Integer integer2=Integer.valueOf(i);
        System.out.println(integer2);

        int i1=integer1.intValue();
        int i2=integer2.intValue();
        System.out.println("和为:"+(i1+i2));

        int num1=integer1;
        int num2=integer2;
        System.out.println("乘积:"+(num1*num2));

        String str2="1.46F";
        Float f=new Float(str2);
        System.out.println(f);
        Float aFloat = Float.valueOf(str2);
        System.out.println(aFloat);
        float parseFloat = Float.parseFloat(str2);
        System.out.println(parseFloat);


        float f1=(float)(1.98);
        System.out.println(f1);
    }

    @Test
    public void test03(){
        int num=9;
        String s = String.valueOf(num);
        Integer integer=new Integer(num);
        System.out.println(integer.toString());
        System.out.println(integer);

        String string = "123";
        int i = Integer.parseInt(string);
        // Integer integer1=new Integer(string);
        Integer integer1=Integer.valueOf(string);
    }

}
