package day13.review;

public class IntegerTest {
    public static void main(String[] args) {
        Integer integer=Integer.valueOf(args[0]);
        int num=Integer.parseInt(args[1]);
        Integer integer1=new Integer(num);

        int num1=integer1.intValue();
        int num2=integer.intValue();
        System.out.println("和为"+(num1+num2));

        int num3=integer;
        int num4=integer1;
        System.out.println("乘积为"+(num3*num4));
    }
}
