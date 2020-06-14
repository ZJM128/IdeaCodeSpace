package day16.review01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        List list=new ArrayList();
        for(int i=0;i<10;i++){
            list.add((int)(Math.random()*50)+1);
        }

        System.out.println("乐透号码已经生成,游戏开始");
        Scanner scanner=new Scanner(System.in);
        List list1=new ArrayList();
      for(int i=1;i<11;){
            System.out.println("请输入第"+i+"个数字[1-50]");
          int number = scanner.nextInt();
          if(number<1 || number>50 ||list1.contains(number) ){
              System.out.println("您输入的数字超过了[1-50] 或者重复了,请从新输入");
              continue;
          }
          list1.add(number);
          i++;
        }
        System.out.println("您输入的号码为:"+list1);
        System.out.println("乐透的号码为:"+list);
        list.retainAll(list1);
        System.out.println("猜中了:"+list.size()+"个数");
    }
}
