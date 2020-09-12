package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
*@Description:给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
*@author:zhijm
*@Date:2020/7/19 21:13
*/
public class TwoSum {
    public static void main(String[] args) {
        int[]nums = {3,2,4};
        System.out.println(Arrays.toString(twoSum1(nums,6)));
    }
    public static int[]twoSum(int[]nums,int target){
        Map<Integer,Integer>map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp=target-nums[i];
            if(map.containsKey(temp)){
                return new int[]{map.get(temp),i};
            }
            map.put(nums[i],i);
        }
        throw new RuntimeException("没有查到数据");
    }

    protected static int[]twoSum1(int[]nums,int target){

        Map<Integer,Integer>map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(target-nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp=target-nums[i];
            if(map.containsKey(temp) && i!=map.get(nums[i])){
                return new int[]{i,map.get(nums[i])};
            }
        }

        throw new RuntimeException("没有查到数");
    }
}
