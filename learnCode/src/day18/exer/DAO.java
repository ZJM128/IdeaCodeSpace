package day18.exer;

import org.junit.Test;

import java.util.*;

public class DAO<T,K> {
    private Map<T,K> map = new HashMap<>();
    public void add(T t,K k){
        map.put(t,k);
    }

    public K getMap(T t){
        return map.get(t);
    }

    public void remove(T t){
        map.remove(t);
    }

    public List<K>getList(){
        Collection<K> values = map.values();
        List<K>  list = new ArrayList<>();
        list.addAll(values);
        return list;
    }

    @Test
    public void test01(){
        DAO<String,Integer>dao = new DAO<>();
        dao.add("李白",12);
        dao.add("白居易",16);
        dao.add("杜甫",18);
        dao.add("陶渊明",20);

        System.out.println(dao.getMap("李白"));
        dao.remove("李白");
        System.out.println("===================");
        List<Integer> list = dao.getList();
        for(Integer integer:list){
            System.out.println(integer);
        }
    }
}
