package atguigu.com;

import org.junit.Test;

import java.util.Scanner;

public class TwoArr {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        System.out.print("请输入第一个数");
//        int num1=sc.nextInt();
//
//        System.out.print("请输入第二个数");
//        int num2=sc.nextInt();
//
//        System.out.print("请输入第三个数");
//        int num3=sc.nextInt();
//
//        if(num1<num2){
//            if(num3<num1)
//                System.out.println(num3+" "+num1+" "+num2);
//            else if(num3>num2)
//                System.out.println(num1+" "+num2+" "+num3);
//            else
//                System.out.println(num1+" "+ num3+" "+num2);
//
//        }else{
//
//            if(num3<num2)
//                System.out.println(num3+" "+num2+" "+num1);
//            else if(num3>num1)
//                System.out.println(num2+" "+num1+" "+num3);
//            else
//                System.out.println(num2+" "+num3+" "+num1);
//
//        }

        Scanner sc=new Scanner(System.in);

        System.out.print("请输入身高");
        int heigth =sc.nextInt();
        System.out.print("请输入财富(万)");
        double money=sc.nextDouble();

        System.out.print("请输入颜值(是/否)");
        String face=sc.next();

        if(heigth>180 && money>=1000 && face.equals("是")){
            System.out.println("我一定嫁给他");
        }else if(heigth>180 || money>=1000 || "是".equals(face)){
            System.out.println("嫁吧 比上不足 比下有余");
        }else{
            System.out.println("不嫁");
        }

    }

    @Test
    public void SwitchText(){
        int a=2;
        switch (a){
            default:
                System.out.println("不匹配");
            case 1:
                System.out.println("1");
            case 3:
                System.out.println("3");
        }

        // 不匹配 1 3
    }
    @Test
    public void Iftext(){
        boolean g=true;
        int a=2,b=4;
        // 本身是boolean
        if(g){
            System.out.println("进来了");
        }

        // == 比较运算符
        if(g==false){
            System.out.println("come in if");
        }else{
            System.out.println("come in else");
        }

        // 赋值运算符 但是其最终的值还是boolean值
        if(g=false){
            System.out.println("come in if");
        }else{
            System.out.println("come in else");
        }

        // 比较运算符 结果是true 或false
        if(a>b){
            System.out.println("come in if");
        }else{
            System.out.println("come in else");
        }
    }

    @Test
    public void iftext2(){
        Scanner sc=new Scanner(System.in);

        System.out.print("请输入身高");
        int heigth =sc.nextInt();
        System.out.print("请输入财富(万)");
        double money=sc.nextDouble();

        System.out.print("请输入颜值(是/否)");
        String face=sc.next();

        if(heigth>180 && money>=1000 && face.equals("是")){
            System.out.println("我一定嫁给他");
        }else if(heigth>180 || money>=1000 || "是".equals(face)){
            System.out.println("嫁吧 比上不足 比下有余");
        }else{
            System.out.println("不嫁");
        }
    }

}
