package day14.review;

import java.util.Scanner;

public class Test08 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入验证码");
        String next = scanner.next();
        System.out.println(next+":验证码是否合法:"+checkStr(next));


    }
    public static boolean checkStr(String str){
        char[] chars = str.toCharArray();
        boolean flag=false;
        int len=chars.length;
        if(len<9){
            return false;
        }
        int count=0;
        for (int i = 0; i < len; i++) {
            if(chars[i]>='A' && chars[i]<='Z'){
                count++;
            }
        }
        if(count<2){
            return false;
        }

        int numberCount=0;
        int leeterCount=0;
        for (int i = 0; i < len; i++) {
            if((chars[i]>='a' && chars[i]<='z') ||(chars[i]>='A'&&chars[i]<='Z')){
              leeterCount++;
            }
            if( chars[i]>='1'&&chars[i]<='9'){
                numberCount++;
            }
        }
        if(numberCount>0 && leeterCount>0){
            return true;
        }else {
            return flag;
        }
    }
}
