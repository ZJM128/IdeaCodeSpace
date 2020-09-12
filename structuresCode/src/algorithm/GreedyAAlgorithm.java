package algorithm;

import java.util.*;
/*
*@Description:贪心算法 每次都是选择最好的(最优的)
*@author:zhijm
*@Date:2020/6/26 10:40
*/
public class GreedyAAlgorithm {
    public static void main(String[] args) {

        // 创建一个map 存放广播电台
        Map<String, Set<String>>broadcasts = new HashMap<>();
        // 将各个电台放入
        HashSet<String>set=new HashSet<>();
        set.add("北京");
        set.add("上海");
        set.add("天津");

        HashSet<String>set1=new HashSet<>();
        set1.add("广州");
        set1.add("北京");
        set1.add("深圳");

        HashSet<String>set2=new HashSet<>();
        set2.add("成都");
        set2.add("上海");
        set2.add("杭州");

        HashSet<String>set3=new HashSet<>();
        set3.add("上海");
        set3.add("天津");

        HashSet<String>set4=new HashSet<>();
        set4.add("杭州");
        set4.add("大连");
        broadcasts.put("k1",set);
        broadcasts.put("k2",set1);
        broadcasts.put("k3",set2);
        broadcasts.put("k4",set3);
        broadcasts.put("k5",set4);
        // 声明一个list存放所有的城市,
        List<String>allAreas= new ArrayList<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("天津");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建一个list存放选择的电台的集合
        List<String>selects=new ArrayList<>();
        // 创建一个存放,选择的电台覆盖的城市和目前还没有覆盖城市的交集
        HashSet<String>tempSet=new HashSet<>();
        // 声明一个maxkey 用于遍历
        String maxKey=null;
        while (allAreas.size()!=0){
            // 没进行遍历则,把指针清空
            maxKey=null;
            for(String key:broadcasts.keySet()){
                // 每进行一次,则要清空中间
                tempSet.clear();
                Set<String> areas = broadcasts.get(key);//取出相应的地区
                tempSet.addAll(areas);// 把选择的地区放入临时的集合中
                // 得到和目前还没有覆盖城市的交集
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量,比maxKey指向的集合地区还多
                //就需要重置maxKey 这个判断体现了贪心算法
                if(tempSet.size()>0 &&
                        (maxKey==null || tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey=key;
                }
            }
            if(maxKey!=null){
                // 加入到选中的key值放入到选中集合中
                selects.add(maxKey);
                // 将maxKey指向的广播电台,从allAreas中去掉
                allAreas.removeAll(broadcasts.get(maxKey));

            }
        }

        System.out.println("得到的选择结果为:"+selects);

    }
}
