package day17;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {
    @Test
    public void test01(){
        List<Integer>list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        ListIterator<Integer> integerListIterator = list.listIterator();
        while (integerListIterator.hasNext()){
                Integer next = integerListIterator.next();
            System.out.print(next+" ");
                if(next==0){
                    integerListIterator.remove();
                }

        }

        System.out.println();
        ListIterator<Integer> integerListIterator1 = list.listIterator();
        while (integerListIterator1.hasNext()){
            Integer next = integerListIterator1.next();
            System.out.print(next+" ");
        }

    }
}
