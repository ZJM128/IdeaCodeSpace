package atguigu.Exer;

import java.util.Scanner;

public class Text8 {
    public static void main(String[]args){

        textDeal1();
        System.out.println("-------");
        textDeal2();

    }


    public static void textDeal1(){
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入年");
        int year=sc.nextInt();

        System.out.println("请输入月份");
        int month=sc.nextInt();

        System.out.println("请输入日期");
        int day=sc.nextInt();
        int sum=0;
        for(int i=1;i<month;i++){
            if(i==12){
                sum+=31;
            }else if(i==11){
                sum+=30;
            }else if(i==10){
                sum+=31;
            }else if(i==9){
                sum+=30;
            }else if(i==8){
                sum+=31;
            }
            if(i==7){
                sum+=31;
            }else if(i==6){
                sum+=30;
            }else if(i==5){
                sum+=31;
            }
            if(i==4){
                sum+=30;
            }else if(i==3){
                sum+=31;
            }else if(i==2){
                if((year%4==0 && year%100!=0) || (year%400==0)){
                    sum+=29;
                }else{
                    sum+=28;
                }
            }else if(i==1){
                sum+=31;
            }
        }

        System.out.println(year+"年"+month+"月"+day+"日"+"是这一年的第"+(sum+day)+"天");

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
