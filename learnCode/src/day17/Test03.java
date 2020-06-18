package day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Test03 {
    public static void main(String[] args) {
        HashMap<String, List<String>>map=new HashMap<>();
        List<String>list=new ArrayList<>();
        list.add("绍兴市");
        list.add("温州市");
        list.add("湖州市");
        list.add("嘉兴市");
        list.add("台州市");
        list.add("金华市");
        list.add("舟山市");
        map.put("浙江省",list);
        List<String>list1=new ArrayList<>();
        list1.add("海口市");
        list1.add("三亚市");
        map.put("海南省",list1);
        List<String>list2=new ArrayList<>();
        list2.add("北京市");
        map.put("北京市",list2);

        Set<String> strings = map.keySet();
        for (String key : strings) {
            System.out.println(key);
            List<String> list3=map.get(key);
            for (String o : list3) {
                System.out.println("\t"+o);
            }
        }
    }
}
