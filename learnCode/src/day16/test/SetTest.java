package day16.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTest {
    private static final Object PRESENT = new Object();
    public static void main(String[] args) {
        List list=new ArrayList();
       list.add(null);
        System.out.println(list.get(0));
        Set set=new HashSet();

        System.out.println(PRESENT);
    }
}
