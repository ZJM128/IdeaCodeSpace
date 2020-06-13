package day13.review;

import org.junit.Test;

import java.util.Scanner;
import java.util.Vector;

public class ScorePrint {
    public static void main(String[] args) {
        Vector vector=new Vector();
        Scanner scanner=new Scanner(System.in);
        for(;;){
            System.out.println("输入负数表示停止录入");
            System.out.print("请录入学生的成绩:");
            String next = scanner.next();
            int i = Integer.parseInt(next);

            if(i<0){
                break;
            }
            vector.add(i);
        }

        int maxScore=Integer.parseInt(vector.get(0).toString());
        for(int i=1;i<vector.size();i++){
            int temp=Integer.parseInt(vector.get(i).toString());
            if(maxScore<temp){
                maxScore=temp;
            }
        }

        for(int j=0;j<vector.size();j++){
            int temp=Integer.parseInt(vector.get(j).toString());
            if(maxScore-temp<10){
                System.out.println("A");
            }else if(maxScore-temp<20){
                System.out.println("B");
            }else if(maxScore-temp<30){
                System.out.println("C");
            }else{
                System.out.println("D");
            }
        }

    }

    @Test
    public void test(){

    }
}
