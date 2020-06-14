package day16.review01;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 */
public class Test06 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("请输入字符串");
        String next = scanner.next();
        LinkedHashSet<Character> linkedHashSet=new LinkedHashSet<>();
        for(int i=0;i<next.length();i++){
            linkedHashSet.add(next.charAt(i));
        }
        System.out.println("str="+next);
        System.out.println("去重后"+linkedHashSet);

    }
}
