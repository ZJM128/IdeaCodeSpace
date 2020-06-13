package atguigu.com;

import java.util.Scanner;

public class SwitchText3 {
//    编写程序：从键盘上读入一个学生成绩，存放在变量score中，根据score的值输出其对应的成绩等级：
//    score>=90 等级：A
//70=<score<90 等级: B
//60=<score<70 等级: C
//    score<60 等级：D

    public static void main(String[]args){
        Scanner sc =new Scanner(System.in);
//        System.out.println("请输入成绩");
//        int score=sc.nextInt();
//        System.out.println("switch");
//        switch (score/10){
//            case 10:
//            case 9:
//                System.out.println("A");
//                break;
//            case 8:
//            case 7:
//                System.out.println("B");
//                break;
//            case 6:
//                System.out.println("C");
//                break;
//            default:
//                System.out.println("D");
//        }
//
//        System.out.println("if ");
//        if(score>=90){
//            System.out.println("A");
//        }else if(score>=70 && score<90){
//            System.out.println('B');
//        }else if(score>=60 && score<70){
//            System.out.println("C");
//        }else{
//            System.out.println("D");
//        }
//

        //    从键盘分别输入年、月、日，判断这一天是当年的第几天
       //    注：判断一年是否是闰年的标准：
      //     1）可以被4整除，但不可被100整除
      //    2）可以被400整除

        System.out.println("请输入年");
        int year =sc.nextInt();
        System.out.println("请输入月");
        int month=sc.nextInt();
        System.out.println("请输入日");
        int day =sc.nextInt();
        int sum=0;
        switch(month){
            case 12:
                sum+=30;
            case 11:
                sum+=31;
            case 10:
                sum+=30;
            case 9:
                sum+=31;
            case 8:
                sum+=31;
            case 7:
                sum+=30;
            case 6:
                sum+=31;
            case 5:
                sum+=30;
            case 4:
                sum+=31;
            case 3:
                if((year%4==0 && year%100!=0) || year % 400 ==0){
                    sum+=29;
                }else{
                    sum+=28;
                }
            case 2:
                sum+=31;
            case 1:
                sum+=day;
                break;

        }
        System.out.println(sum);
//        switch (month){
//            case 1:
//                System.out.println(day);
//                break;
//            case 2:
//                System.out.println(31+day);
//            case 3:
//                System.out.println(31+twoDay+day);
//            case 4:
//                System.out.println(31+twoDay+31+day);
//                break;
//            case 5:
//                System.out.println(31+twoDay+31+30+day);
//                break;
//            case 6:
//                System.out.println(31+twoDay+31+30+31+day);
//                break;
//            case 7:
//                System.out.println(31+twoDay+31+30+31+30+day);
//                break;
//            case 8:
//                System.out.println(31+twoDay+31+30+31+30+31+day);
//                break;
//            case 9:
//                System.out.println(31+twoDay+31+30+31+30+31+31+day);
//                break;
//            case 10:
//                System.out.println(31+twoDay+31+30+31+30+31+31+30+day);
//                break;
//            case 11:
//                System.out.println(31+twoDay+31+30+31+30+31+31+30+31+day);
//                break;
//            case 12:
//                System.out.println(31+twoDay+31+30+31+30+31+31+30+31+30+day);
//                break;
//        }

    }
}
