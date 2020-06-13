package atguigu.Exer;

import java.util.Scanner;

public class Text9 {
    public static void main(String []args){
           textDeal1();
        System.out.println("============");
        textDeal2();
    }

    public static void textDeal1(){
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入年份");
        int year = sc.nextInt();

        System.out.println("请输入月份");
        int month = sc.nextInt();

        System.out.println("请输入日期");
        int day = sc.nextInt();

        //计算今天到 2000年1月1日有多少天

        // 计算年
        int yearMinus = year - 2000;
        int yearCount = 0;
        for (int i = 1; i <= yearMinus; i++) {
            year = 2000 + i;
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                yearCount += 366;
            } else {
                yearCount += 365;
            }
        }

        // 计算月
        int monthCount = 0;
        for (int i = 1; i < month; i++) {
            if (i == 1) {
                monthCount += 31;
            } else if (i == 2) {
                monthCount += 30;
            } else if (i == 3) {
                monthCount += 31;
            } else if (i == 4) {
                monthCount += 30;
            } else if (i == 5) {
                monthCount += 31;
            } else if (i == 6) {
                monthCount += 30;
            } else if (i == 7) {
                monthCount += 31;
            } else if (i == 8) {
                monthCount += 31;
            } else if (i == 9) {
                monthCount += 30;
            } else if (i == 10) {
                monthCount += 31;
            } else if (i == 11) {
                monthCount += 30;
            } else if (i == 12) {
                monthCount += 31;
            }
        }

        int allDays = yearCount + monthCount + day;
        System.out.println(allDays);
        int flag = allDays % 5;
        switch (flag) {
            case 1:
            case 2:
            case 3:
                System.out.println("打鱼🐠");
                break;
            case 0:
            case 4:
                System.out.println("晒网");
        }
    }
    public static void textDeal2(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入年份");
        int year=sc.nextInt();
        System.out.println("请输入月份");
        int month=sc.nextInt();

        System.out.println("请输入日期");
        int day=sc.nextInt();

        // 声明总天数 默认位当天日期
        int days=day;

        // 计算月份的总天数
        for(int i=1;i<=12;i++){
            if(i==4 || i==6 || i==9 || i==11){
                days+=30;
            }else if(i==2){
                boolean boo=checkIsFullYear(year);
                if(boo){
                    days+=29;
                }else{
                    days+=28;
                }
            }else{
                days+=31;
            }
        }

        // 计算年份的天数
        for(int i=2000;i<=year;i++){
            boolean boo=checkIsFullYear(year);
            if(boo){
                days+=366;
            }else{
                days+=365;
            }
        }

        if(days%5==1 || days%5==2 || days%5==3){
            System.out.println("打鱼");
        }else{
            System.out.println("晒网");
        }

    }

    public  static boolean checkIsFullYear(int year){
        if((year%4==0 && year%100!=0) || year%400==0){
            return true;
        }
        return false;
    }
}
