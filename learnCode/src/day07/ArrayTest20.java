package day07;

import java.util.Scanner;

public class ArrayTest20 {
    public static void main(String []args){
//        int []arr=new int[20];
//        intArray(arr);
//        for(int a:arr){
//            System.out.println(a);
//        }
        selectMaxScore();
    }
    public static void intArray(int []arr){
        for(int i=0;i<arr.length;i++){
            arr[i]=i*2+20;
        }
    }
    public static void selectMaxScore(){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入学生的人数:");
        int num=sc.nextInt();
        int score=0;
        int []arr=new int[num];
        System.out.println("请输入"+num+"个成绩:");
        for(int i=0;i<arr.length;i++){
            score=sc.nextInt();
            arr[i]=score;
        }

        int maxScore=arr[0];
        for(int i=1;i<arr.length;i++){
            maxScore= maxScore>arr[i]?maxScore:arr[i];
            /*if(maxScore<arr[i]){
                maxScore=arr[i];
            }*/
        }
        System.out.println("最高分是"+maxScore);

        char level=1;
        for(int i=0;i<arr.length;i++){
                if(arr[i]>=maxScore-10){
                    level='A';
                   // System.out.println(score1+" A");
                }else if(arr[i]>=maxScore-20){
                    level='B';
                    //System.out.println(score1+" B");
                }else if(arr[i]>=maxScore-30){
                    level='C';
                    //System.out.println(score1+" C");
                }else{
                    level='D';
                    //System.out.println(score1+" D");
                }
                System.out.println("Student "+i +" 成绩:"+arr[i]+" 等级 "+level);
        }
    }
}
