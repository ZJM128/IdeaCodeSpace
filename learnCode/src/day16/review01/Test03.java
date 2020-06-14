package day16.review01;

import java.util.ArrayList;
import java.util.List;

public class Test03 {
    public static void main(String[] args) {
        List list=new ArrayList();
        for(int i=0;i<30;i++){
            list.add((int)(Math.random()*99)+2);
        }
        List list1=new ArrayList();
        boolean flag=true;
        for(int i=0;i<list.size();i++){
            int number=(int)list.get(i);
            for(int j=2;j<=Math.sqrt(number);j++){
                if(number%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag){
                list1.add(number);
            }
            flag=true;
        }
        System.out.println("随机数为:"+list);
        System.out.println("其中的质数为:"+list1);
    }
}
