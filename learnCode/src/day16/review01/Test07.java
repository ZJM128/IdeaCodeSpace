package day16.review01;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class Test07 {
    public static void main(String[] args) {

        TreeSet<Integer>treeSet=new TreeSet<>();

        Random random=new Random();
        for(int i=0;i<6;i++){
            treeSet.add(random.nextInt(33)+1);
        }
        ArrayList<Integer>list=new ArrayList<>();
        list.addAll(treeSet);
        list.add(random.nextInt(16)+1);
        System.out.println("所有的双色球"+list);
        System.out.print("红色号码:");
        for(int i=0;i<list.size()-1;i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println("蓝色号码为:"+list.get(list.size()-1));
    }
}
