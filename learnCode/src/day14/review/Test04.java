package day14.review;

import java.util.Scanner;

public class Test04 {
    public static void main(String[] args) {
        System.out.print("请输入源字符串:");
        Scanner scanner=new Scanner(System.in);
        String scrStr=scanner.next();
        System.out.print("请输入要删除的字符串:");
        String delStr = scanner.next();
        test1(scrStr,delStr);
        int count=0;
        int index=0;
        while ((index= scrStr.indexOf(delStr,index))!=-1){
            count++;
            index++;
        }
        System.out.println("源字符串总共包含:"+count+"个"+delStr);
        System.out.println("删除"+delStr+"后的字符串为:"+scrStr.replace(delStr,""));
    }

    public static void test1(String scrStr,String delStr){
        String result=scrStr.replaceAll(delStr,"");
        int count=(scrStr.length()-result.length())/delStr.length();
        System.out.println("个数"+count);
        System.out.println("删除后的"+result);
    }

}
