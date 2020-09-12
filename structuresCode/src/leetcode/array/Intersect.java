package leetcode.array;

import java.util.*;

/*
*@Description:给定两个数组，编写一个函数来计算它们的交集。
*@author:zhijm
*@Date:2020/7/15 14:03
*/
public class Intersect {
    public static void main(String[] args) {
        //[4,9,5]
        //[9,4,9,8,4]
        int[]nums1= {1,1}, nums2 = {1,2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }
    public static int[] intersect(int[]nums1,int[]nums2){

        int[]minarr=nums1.length<=nums2.length?nums1:nums2;
        int[] maxarr=nums1.length>nums2.length?nums1:nums2;
        Map<Integer,Integer>map1 = new HashMap<>();
        Map<Integer,Integer>map2 = new HashMap<>();
        for (int i : minarr) {
            Integer integer = map1.get(i);
            if(integer!=null){
               integer+=1;
            }else {
                integer=1;
            }
            map1.put(i,integer);
        }
        for (int i : maxarr) {
            Integer integer = map2.get(i);
            if(integer!=null){
                integer+=1;
            }else {
                integer=1;
            }
            map2.put(i,integer);
        }
        List<Integer>list = new ArrayList<>();
        Set<Integer> set = map2.keySet();
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            Integer key=iterator.next();
            Integer value2 = map2.get(key);
            Integer value1 = map1.get(key);
            if(value1!=null){
                value1=value1<value2?value1:value2;
                for(int i = 0;i<value1;i++){
                    list.add(key);
                }
            }
        }
        int[]arr=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }
}
