package day14.review;

import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入QQ号:");
        String next = scanner.next();
        System.out.print("这个QQ号是否正确:");
        if(next.matches("[1-9][0-9]{4,11}")){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
        int leng=next.length();
        if((leng>=5 && leng<=12) && !next.startsWith("0")){
            System.out.println("true");
        }else{
            System.out.println("false");
        }

    }
}
