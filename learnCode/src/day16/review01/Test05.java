package day16.review01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Test05 {
    public static void main(String[] args) {
        List<Character>list=new ArrayList<>();
        for(int i='a';i<='z';i++){
            list.add((char)i);
        }
        Random random=new Random();
        List<Character>list1=new ArrayList<>();
        System.out.println(random.nextInt(26));
        System.out.println(list.get(19));
        for(int i=0;i<30;i++){
            list1.add(list.get(random.nextInt(26)));
        }
        for (Character character : list1) {
            System.out.print(character+" ");
        }
        System.out.println();
        System.out.println("a:"+listTest(list1, "a"));;
    }
    public static int listTest(Collection collection,String s){
        List<Character>list =(List<Character>)collection;
        int count=0;
        for (int i = 0; i < list.size(); i++) {
             char temp=s.charAt(0);
             if(list.get(i)==temp){
                 count++;
             }
        }
        return count;
    }
}
