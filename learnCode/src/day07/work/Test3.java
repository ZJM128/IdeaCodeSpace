package day07.work;

import java.util.Scanner;

public class Test3 {
    public static void main(String []args){
        printScore();
    }

    public static void printScore(){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入学生的数量:");
        int number=sc.nextInt();
        int []scoreArr=new int [number];
       System.out.println("请输入"+number+"个成绩");
       int score=0;
       for(int i=0;i<number;i++){
           score=sc.nextInt();
           scoreArr[i]=score;
       }
       int max=scoreArr[0];
       for(int i=1;i<scoreArr.length;i++){
           if(max<scoreArr[i]){
               max=scoreArr[i];
           }
       }
       System.out.println("最高成绩为:"+max);
        char level=0;
       for(int i=0;i<scoreArr.length;i++){
           if(scoreArr[i]>=max-10){
               level='A';
           }else if(scoreArr[i]>=max-10){
               level='B';
           }else if(scoreArr[i]>=max-10){
               level='C';
           }else {
               level='d';
           }
           System.out.println("学生"+i+"成绩:"+scoreArr[i]+"等级"+level);
       }


    }
}
