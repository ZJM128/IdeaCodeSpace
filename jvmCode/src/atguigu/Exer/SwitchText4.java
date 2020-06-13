package atguigu.Exer;

import java.util.Scanner;

public class SwitchText4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入成绩");
        int score=sc.nextInt();

        switch(score/10){
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
                System.out.println("及格");
                break;
            default:
                System.out.println("不及格");
        }
    }
}
