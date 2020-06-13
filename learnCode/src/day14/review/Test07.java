package day14.review;

import java.util.Scanner;

/**
 * 字符串回文
 */
public class Test07 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入字符串");
        String next = scanner.next();
        //System.out.println("回文:"+circulationStr(next));
        System.out.println("回文:"+circulationStr1(next));

    }

    public static boolean circulationStr(String str){
        char[] chars = str.toCharArray();
       boolean flag=true;
        for (int i = 0; i < chars.length/2; i++) {
            if(chars[i]!=chars[chars.length-1-i]){
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static boolean circulationStr1(String str){
        StringBuffer stringBuffer=new StringBuffer(str);
        stringBuffer.reverse();
        return str.length()==stringBuffer.length();
    }
}
