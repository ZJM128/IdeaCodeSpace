package day17;

import java.util.*;

public class Test02 {
    public static void main(String[] args) {
        HashMap<String, List<String>>map=new HashMap<>();
        List<String>list = new ArrayList<>();
        list.add("<一路有你>");
        list.add("<吻别>");
        list.add("<一千伤心的理由>");
        List<String>list1 = new ArrayList<>();
        list1.add("<一红豆>");
        list1.add("<传奇>");
        list1.add("<容易受伤的女人>");
        map.put("张学友",list);
        map.put("王菲",list1);
        Set<Map.Entry<String,List<String>>>set=map.entrySet();
        for (Map.Entry<String, List<String>> entry : set) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }




    }
}
