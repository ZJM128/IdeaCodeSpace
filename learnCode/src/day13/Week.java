package day13;

import java.util.Scanner;

/*
*@Description:声明Week枚举类，其中包含星期一至星期日的定义；
在TestWeek类中声明方法中printWeek(Week week)，根据参数值打印相应的中文星期字符串。
提示，使用switch语句实现。
在main方法中从命令行接收一个1-7的整数(使用Integer.parseInt方法转换)，分别代表星期一至星期日，打印该值对应的枚举值，然后以此枚举值调用printWeek方法，输出中文星期。
*@author:zhijm
*@Date:2020/6/9 11:40
*/
public enum Week {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}

class TestWeek{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int day=scanner.nextInt();
       /* while (true){
            System.out.println("请输入1-7数字");
            System.out.println("请输入其他退出");
            int week=scanner.nextInt();
            if(week==1){
                printWeek(Week.MONDAY);
            }else if(week==2){
                printWeek(Week.TUESDAY);
            }else if(week==3){
                printWeek(Week.WEDNESDAY);
            }else if(week==4){
                printWeek(Week.THURSDAY);
            }else if(week==5){
                printWeek(Week.FRIDAY);
            }else if(week==6){
                printWeek(Week.SATURDAY);
            }else if(week==7){
                printWeek(Week.SUNDAY);
            }else{
                break;
            }
        }*/
        Week monday = Week.valueOf("MONDAY");
        System.out.println(monday);
        Week[] values = Week.values();
        printWeek(values[day-1]);
    }
    public  static void printWeek(Week week){
        switch(week){
            case MONDAY:
                System.out.println("星期一");
                break;
            case TUESDAY:
                System.out.println("星期二");
                break;
            case WEDNESDAY:
                System.out.println("星期三");
                break;
            case THURSDAY:
                System.out.println("星期四");
                break;
            case FRIDAY:
                System.out.println("星期五");
                break;
            case SATURDAY:
                System.out.println("星期六");
                break;
            case SUNDAY:
                System.out.println("星期天");
                break;
            default:
                System.out.println("没有对应的数");
                break;
        }
    }
}