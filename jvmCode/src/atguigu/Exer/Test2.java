package atguigu.Exer;

import java.util.Scanner;

public class Test2 {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入成绩");
        int score=sc.nextInt();
        if(score==100){
            System.out.println("奖励宝马车");
        }else if(score>=80 && score<100){
            System.out.println("奖励一台苹果手机");
        }else if(score>=60 && score<80){
            System.out.println("奖励一本参考书");
        }else{
            System.out.println("啥都奖励");
        }
    }
}
