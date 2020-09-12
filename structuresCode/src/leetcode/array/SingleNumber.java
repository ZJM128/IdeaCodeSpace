package leetcode.array;

import java.util.*;

/*
*@Description:给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
* 输入: [4,1,2,1,2]
输出: 4
*@author:zhijm
*@Date:2020/7/14 22:35
*/
public class SingleNumber {
    public static void main(String[] args) {
        int[]nums={1,4,2,1,2};
        System.out.println(singleNumber01(nums));
    }
    public static int singleNumber(int[] nums){
        Map<Integer,Integer>map = new HashMap<>();
        for (int num : nums) {
            Integer sum=map.get(num);
            if(map.containsKey(num)){
                sum+=1;
            }else{
                sum=1;
            }
            map.put(num,sum);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if(next.getValue()<=1){
                return next.getKey();
            }
        }
       throw new RuntimeException("不存在");
    }

    /**
     * 除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
     * @param nums
     * @return
     */
    public static int singleNumber01(int[]nums){

        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 异或最终得出一个只有一个元素的数据 异或是相同则抵消
            ans = ans ^ nums[i];
        }
        return ans;
    }
}
