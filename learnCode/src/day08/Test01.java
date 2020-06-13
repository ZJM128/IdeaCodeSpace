package day08;

public class Test01 {
    public static void main(String[]args){
        int a=2;
        int b=3;
        a=a^b;
        b=a^b;//-->a^b^b
        a=a^b;//-->a^b^a^b^b

        System.out.println(a);
        System.out.println(b);
    }
}
