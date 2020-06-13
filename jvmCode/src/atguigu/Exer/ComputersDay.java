package atguigu.Exer;

import java.util.Scanner;

public class ComputersDay {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入月");
        int month =sc.nextInt();

        System.out.println("请输入日期");
        int day=sc.nextInt();
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
                sum+=28;
            case 2:
                sum+=31;
            case 1:
                sum+=day;

        }
       // week=week+sum-1;// 比较关键
        //int dayn=week%7;
        int dayn=(sum%7)+1;
        if(dayn==1){
            System.out.println("星期一");
        }else if(dayn==2){
            System.out.println("星期二");
        }else if(dayn==3){
            System.out.println("星期三");
        }else if(dayn==4){
            System.out.println("星期四");
        }else if(dayn==5){
            System.out.println("星期五");
        }else if(dayn==6){
            System.out.println("星期六");
        }else if(dayn==7 || dayn==0){
            System.out.println("星期日");
        }

    }
}
