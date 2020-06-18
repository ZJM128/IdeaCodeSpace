package day18.exer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverTest {

    public static <T>void rever(T[]arr){
        List<T> list = Arrays.asList(arr);
        Collections.reverse(list);
        Object[] objects = list.toArray();
        for (Object o:objects){
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Integer[] arr={1,9,2,6,3};
        rever(arr);
        System.out.println("==============");
        String[]arrs={"888","995","45"};
        rever(arrs);
    }
}
