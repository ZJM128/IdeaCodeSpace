package day14.review;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入需要反转的字符");
        String next = scanner.next();
        System.out.println("录入的字符串:"+next);
        System.out.println("反转后的字符串:"+reverse(next));

        System.out.println("反转后的字符串:"+ reverse1(next));

    }
    public  static String reverse(String str){
        char[] chars = str.toCharArray();
        for(int i=0,j=chars.length-1;i<j;i++,j--){
            char temp=chars[i];
            chars[i]=chars[j];
            chars[j]=temp;
        }
        return new String(chars,0,chars.length);
    }
    public static String reverse1(String str){
        StringBuffer sb=new StringBuffer(str);
        sb.reverse();
        return sb.toString();
    }
}
