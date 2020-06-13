package day13.review;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*Integer i1 = 128;
        Integer i2 = 128;
        int i3 = 128;
        int i4 = 128;
        System.out.println(i1 == i2);// false
        System.out.println(i3 == i4);// true
        System.out.println(i1 == i3);//true*/
       /* double a = 2.0;
        double b = 2.0;
        Double c = 2.0;
        Double d = 2.0;
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(a == d);*/

        Scanner scanner=new Scanner(System.in);
        int nextInt = scanner.nextInt();

        Month value = Month.getByValue(nextInt);
        System.out.println(value);
    }

    @org.junit.Test
    public void test01(){
        Color color=Color.GREEN;
        System.out.println(color);
    }

    @org.junit.Test
    public void test(){
        Payment[] values = Payment.values();
        for (Payment value : values) {
            value.pay();
        }
    }
}
