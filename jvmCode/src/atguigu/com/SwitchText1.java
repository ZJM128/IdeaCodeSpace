package atguigu.com;
import org.junit.Test;

import java.util.Scanner;
public class SwitchText1 {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);

        int day =sc.nextInt();
        switch(day){
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:System.out.println("星期三");
                break;
            case 4:System.out.println("星期四");
                break;
            case 5:System.out.println("星期五");
                break;
            case 6:System.out.println("星期六");
                break;
            case 7:System.out.println("星期七");
                break;
            default:
                System.out.println("非法参数");

        }
        System.out.println("请输入字母");
        char ch=sc.next().charAt(0);
        switch(ch){
            case 'a':
                System.out.println((char)('a'-('a'-'A')));
                break;
            case 'b':
                System.out.println((char)('b'-('b'-'B')));
                break;
            case 'c':
                System.out.println((char)('c'-('c'-'C')));
                break;
            default:
                System.out.println("other");
        }

        System.out.println("请输入成绩");
       int soce =sc.nextInt();
       switch(soce/10){
           case 10:
           case 9:
           case 8:
           case 7:
           case 6:
               System.out.println("合格");
               break;
           default:
               System.out.println("不合格");
       }

        System.out.println("请输入月份");
       int month=sc.nextInt();
       switch(month){
           case 3:
           case 4:
           case 5:
               System.out.println("春季");
               break;
           case 6:
           case 7:
           case 8:
               System.out.println("夏季");
               break;
           case 9:
           case 10:
           case 11:
               System.out.println("秋季");
               break;
           case 12:
           case 1:
           case 2:
               System.out.println("冬季");
               break;
       }
    }

    @Test
    public void checkDay(){
        Scanner sc=new Scanner(System.in);
        int day =sc.nextInt();
        switch(day){
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:System.out.println("星期三");
              break;
            case 4:System.out.println("星期四");
                break;
            case 5:System.out.println("星期五");
                break;
            case 6:System.out.println("星期六");
                break;
            case 7:System.out.println("星期七");
                break;
            default:
                System.out.println("非法参数");

        }
    }
}
