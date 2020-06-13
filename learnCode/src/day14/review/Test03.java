package day14.review;

import java.util.Scanner;

public class Test03 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请录入大字符串");
        String bigStr=scanner.next();
        System.out.println("请输入小字符串");
        String smallStr=scanner.next();
       int count= count(bigStr,smallStr);
        System.out.println("小字符串"+smallStr+"在大字符串中共出现的次数"+count);

    }

    public static int count(String digStr,String smallStr){
        int count=0;
        int index=0;
        while((index=digStr.indexOf(smallStr,index))!=-1){
            count++;
            index++;
        }
        return count;
    }
}
