package day25;

public class StringTest {
    public static void main(String[] args) {
        String str1=new String("123").intern();
        String str2=new String("123").intern();
        String str3="123";
        System.out.println(str1==str3);
        System.out.println(str1==str2);
    }
}
