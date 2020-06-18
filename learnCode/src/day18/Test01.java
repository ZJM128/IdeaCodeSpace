package day18;

import org.junit.Test;

import java.util.*;

/**
 * 编写程序，在main方法中接收5个参数整数字符串；
 * 创建TreeSet类型的集合（使用泛型），将5个字符串以整数形式添加到集合中；
 * 增强型for循环遍历该集合，打印所有元素，并将所有元素之和打印出来
 */
public class Test01 {
    public static void main(String[] args) {
        TreeSet<Integer>treeSet=new TreeSet<>();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(6);
        treeSet.add(5);
        int count=0;
        for(Integer integer:treeSet){
            System.out.println(integer);
            count+=integer;
        }

        System.out.println("和为:"+count);
    }
    @Test
    public void test01(){
        Map<String,Integer> map = new HashMap<>();
        map.put("李白",52);
        map.put("白居易",52);
        map.put("杜甫",42);
        map.put("李贺",22);
        map.put("游子",32);
        map.put("苏轼",62);

        // keySet
        Set<String> set = map.keySet();
        for(String str:set){
            System.out.println(str);
        }

        System.out.println("===============================");
        Iterator<String>iterator;
        iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        // values
      Collection<Integer>collection= map.values();
        for(Integer integer :collection){
            System.out.println(integer);
        }
        System.out.println("==================================");
        Iterator<Integer>it=collection.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        // entrySet
        Set<Map.Entry<String,Integer>>set1 = map.entrySet();

        for(Map.Entry<String,Integer> entry:set1){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
        System.out.println("============================");
        Iterator<Map.Entry<String, Integer>> it1 = set1.iterator();
        while(it1.hasNext()){
            Map.Entry<String, Integer> entry = it1.next();
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }
}
