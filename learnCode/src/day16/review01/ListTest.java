package day16.review01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(12);
        list.add(new String("12"));
        list.add("李白");
        list.add("AAA");

        // 指定的位置插入元素
        list.add(1,"BB");
        //System.out.println(list);

      // List list1= Arrays.asList("1",169,"BB","UU");
        List list1=new ArrayList();
        list1.add("bb");
        list1.add("cc");
       // list.add(2,list1);// 此方法是把list1当做一个元素了
        //System.out.println(list);

        //list.addAll(1,list1);
        //System.out.println(list);// 此方法是把list1中的每个元素一个一个插进去

        //
       // Object o = list.get(1);
        //System.out.println(o);

        int index = list.indexOf("BB");
       // System.out.println(index);
       // System.out.println(list.lastIndexOf("BB"));

        list.add(3,2);
        list.add(2,6);
        //System.out.println(list);
        //list.remove(1);
        list.remove(2);//  在
        //System.out.println(list);

        list.set(1,"TTT");
        //System.out.println(list);

        LinkedList list2=new LinkedList();
        list2.add("HH");
        list2.add("GG");
        list2.add("KK");

        list2.addFirst("UU");
        System.out.println(list2);
        list2.addLast("OO");
        System.out.println(list2);
        list2.removeFirst();
        System.out.println(list2);
        list2.removeLast();
        System.out.println(list2);


    }
}
