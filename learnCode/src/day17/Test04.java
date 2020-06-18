package day17;

import java.util.*;

public class Test04{
    public static void main(String[] args) {
        HashMap<Integer,String>map=new HashMap<>();
        ArrayList<Integer>list=new ArrayList<>();
        String[] nums = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };
        String[] colors = { "♦", "♣", "红桃", "黑桃" };
       int count=1;
        for (int i=0;i<colors.length;i++){
            for (int j=0;j<nums.length;j++){
                map.put(count,colors[i]+nums[j]);
                list.add(count);
                count++;
            }
        }
        map.put(53,"大王");
        map.put(54,"小王");
        list.add(53);
        list.add(54);
       // 打乱牌
        Collections.shuffle(list);
        TreeSet<Integer>treeSet1=new TreeSet<>();
        TreeSet<Integer>treeSet2=new TreeSet<>();
        TreeSet<Integer>treeSet3=new TreeSet<>();
        TreeSet<Integer>treeSet4=new TreeSet<>();

        for(int i=0;i<13;i++){
            treeSet1.add(list.remove(0));
            treeSet2.add(list.remove(0));
            treeSet3.add(list.remove(0));
            treeSet4.add(list.remove(0));
        }

        System.out.println("朱茵的牌");
        for(Integer i:treeSet1){
            System.out.print(map.get(i)+"\t");
        }

        System.out.println();
        System.out.println("林志玲的牌");
        for(Integer i:treeSet2){
            System.out.print(map.get(i)+"\t");
        }
        System.out.println();
        System.out.println("刘亦菲的牌");
        for(Integer i:treeSet3){
            System.out.print(map.get(i)+"\t");
        }
        System.out.println();
        System.out.println("我的的牌");
        for(Integer i:treeSet4){
            System.out.print(map.get(i)+"\t");
        }
        System.out.println();
        System.out.println("底牌是");
        for (Integer k:list){
            System.out.print(map.get(k)+"\t");
        }

    }
}
