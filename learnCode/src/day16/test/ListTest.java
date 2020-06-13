package day16.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);

        System.out.println(list);
        list.add(1,"ab");
        System.out.println(list);
        list.add(0,"DD");
        System.out.println(list);

        List list2=new ArrayList();
        list2.add("LL");
        list2.add("HHH");
        list2.add("HHH");
        list.addAll(0,list2);
        System.out.println(list);

        Object o = list.get(0);
        System.out.println(o);
        System.out.println(list);

        int index = list.indexOf("HHH");// 从前往后找
        System.out.println(index);

        int index1 = list.lastIndexOf("HHH");// 从后往前找
        System.out.println(index1);

        System.out.println(list);
        Object remove = list.remove(2);// 返回删除前的元素
        System.out.println(remove);
        boolean flag = list.remove("HHH");
        System.out.println(flag);
        System.out.println("----------------------");
        System.out.println(list);

        Object setNun = list.set(2, "oooo");// 返回修改前的元素
        System.out.println(setNun);
        System.out.println(list);

        System.out.println("============================");
        List list1 = list.subList(0, 2);
        System.out.println(list1);
        System.out.println(list);
    }
}
