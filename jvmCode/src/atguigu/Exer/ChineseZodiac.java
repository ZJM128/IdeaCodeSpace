package atguigu.Exer;

import java.util.Scanner;

public class ChineseZodiac {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入年份");
        int year=sc.nextInt();

        switch(year%12){
            case 1:
                System.out.println("🐔");
                break;
            case 2:
                System.out.println("狗");
                break;
            case 3:
                System.out.println("🐖");
                break;
            case 4:
                System.out.println("🐭");
                break;
            case 5:
                System.out.println("🐮");
                break;
            case 6:
                System.out.println("🐅");
                break;
            case 7:
                System.out.println("🐰");
                break;
            case 8:
                System.out.println("🐉");
                break;
            case 9:
                System.out.println("🐍");
                break;
            case 10:
                System.out.println("🐴");
                break;
            case 11:
                System.out.println("🐐");
                break;
            case 12:
                System.out.println("🐵");
                break;

        }
    }
}
