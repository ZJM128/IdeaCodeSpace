package atguigu.Exer;

import java.util.Scanner;

public class SwitchText1 {
    public static void main(String []args){
        // break 和 default都可以省了
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入数字");
        int num=sc.nextInt();
        switch(num){
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("three");
                break;
            default:
                System.out.println("无对应的数字");
                break;
        }
    }
}
