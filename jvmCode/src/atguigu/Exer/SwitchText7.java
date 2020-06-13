package atguigu.Exer;

import java.util.Scanner;

/**
 * 编写程序：从键盘上读入一个学生成绩，存放在变量score中，根据score的值输出其对应的成绩等级：
 *
 * score>=90           等级：A
 * 70=<score<90     等级: B
 * 60=<score<70     等级: C
 * score<60             等级：D
 */
public class SwitchText7 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入成绩");
        int score =sc.nextInt();

        switch(score/10){
            case 9:
                System.out.println("A");
                break;
            case 8:
            case 7:
                System.out.println("B");
                break;
            case 6:
                System.out.println("C");
                break;
            default:
                System.out.println("D");
                break;


        }
    }
}
