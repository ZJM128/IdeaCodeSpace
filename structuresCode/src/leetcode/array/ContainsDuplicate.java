package leetcode.array;

import java.util.HashSet;
import java.util.Set;

/*
*@Description:给定一个整数数组，判断是否存在重复元素。
如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
*@author:zhijm
*@Date:2020/7/14 7:25
*/
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[]arr={1,1,1,3,3,4,3,2,4,2};
        System.out.println(containsDuplicate(arr));
    }

    public static  boolean containsDuplicate(int[]nums){

        Set<Integer> set=new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }
}
