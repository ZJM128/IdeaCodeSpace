package day17;

import org.junit.Test;

import java.util.*;

public class MapTest02 {
    /**
     * 遍历三
     * 获取map中的所有的Entry组成的set
     * EntrySet
     */
    @Test
    public void test06(){

        Map<String,Integer> map=new HashMap<>();
        map.put("aa",11);
        map.put("bb",11);
        map.put("cc",11);
        map.put("dd",11);
       Set<Map.Entry<String,Integer>> set = map.entrySet();
       Iterator<Map.Entry<String,Integer>>iterator= set.iterator();
       while(iterator.hasNext()){
           Map.Entry<String,Integer>entry=iterator.next();
           System.out.println(entry.getKey()+"="+entry.getValue());
       }
    }
    /**
     * 遍历2
     * 获取map中所有的value组成的collection
     * values()
     */
    @Test
    public void test05(){
        Map<String,Integer> map=new HashMap<>();
        map.put("aa",11);
        map.put("bb",11);
        map.put("cc",11);
        map.put("dd",11);
        Collection<Integer> values = map.values();
        for(Integer integer:values){
            System.out.println(integer);
        }
        System.out.println("========================");
       Iterator<Integer> integerIterator= values.iterator();
       while (integerIterator.hasNext()){
           System.out.println(integerIterator.next());
       }
    }
    /**
     * 遍历方法一,获取map中所有的key组成的set
     * keySet()
     */
    @Test
    public void test01(){
        Map<String,Integer> map=new HashMap<>();
        map.put("aa",11);
        map.put("bb",11);
        map.put("cc",11);
        map.put("dd",11);

        Set<String> set=map.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("==============");
        for(String o:set){
            System.out.println(o);
        }
    }
}
