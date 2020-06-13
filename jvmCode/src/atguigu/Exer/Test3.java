package atguigu.Exer;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入第一个数字");
        int num1=sc.nextInt();
        System.out.println("请输入第二个数字");
        int num2=sc.nextInt();
        System.out.println("请输入第三个数字");
        int num3=sc.nextInt();

        if(num1<num2){
            if(num3<num1){
                System.out.println(num3+" "+num1+" " +num2);
            }else if(num3>num2){
                System.out.println(num1+" "+num2+" "+num3);
            }else{
                System.out.println(num1+" "+num3+" "+num2);
            }
        }else{
            if(num3<num2){
                System.out.println(num3+" "+num2+" " +num1);
            }else if(num3>num1){
                System.out.println(num2+" "+num1+" "+num3);
            }else{
                System.out.println(num2+" "+num3+" "+num1);
            }
        }
    }
}
