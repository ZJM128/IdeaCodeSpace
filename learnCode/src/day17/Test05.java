package day17;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Test05 {
    public static void main(String[] args) {
        HashMap<Integer,String>map=new HashMap<>();
        map.put(1930,"乌拉圭");
        map.put(1934,"意大利");
        map.put(1938,"意大利");
        map.put(1950,"乌拉圭");
        map.put(1954,"西德");
        map.put(1958,"巴西");
        map.put(1962,"巴西");
        map.put(1966,"英格兰");
        map.put(1970,"巴西");
        map.put(1978,"乌拉圭");
        map.put(1982,"西德");
        map.put(1986,"乌拉圭");

        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入一个年份:");
        int year=scanner.nextInt();
        Set<Integer> set = map.keySet();

        String str = map.get(year);
        if(str==null){
            System.out.println("没有举办世界杯");
        }else{
            System.out.println(year+"年"+"获取冠军:"+str);
        }
        System.out.print("请输入一个国家名:");
        String name=scanner.next();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            String value = entry.getValue();
            if(name.equals(value)){
                System.out.println(entry.getKey());
            }
        }

    }


}
