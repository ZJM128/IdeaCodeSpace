package day18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListRemove {
    @Test
    public void test01(){
        List<Integer> list=new ArrayList<>();
        list.add(42);
        list.add(2);
        System.out.println(list.remove(new Integer(2)));
    }
    @Test
    public void test02(){
        List scr=new ArrayList();
        scr.add(12);
        scr.add(13);
        scr.add(14);
        List dest=new ArrayList();
        Collections.copy(scr,dest);
        System.out.println(dest);
    }
}
