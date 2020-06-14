package day16.review01;

import java.util.ArrayList;
import java.util.List;

public class Test04 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList();
        for(int i=0;i<10;i++){
            list.add((int)(Math.random()*100)+1);
        }
        System.out.println("10个随机数:"+list);
        List<Integer> list1=new ArrayList();
        int max=list.get(0);
        int index=0;
        for(int j=0;j<3;j++) {
            for (int i = 0; i < list.size(); i++) {
                if(max<list.get(i)){
                    index=i;
                    max=list.get(i);
                }
            }
            list.remove(index);
            index=0;
            list1.add(max);
            max=list.get(0);
        }
        System.out.println("前3个最大值"+list1);
        System.out.println("删除后"+list);
    }
}
