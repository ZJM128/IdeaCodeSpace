package leetcode.array;

import java.util.Arrays;

/*
*@Description:给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
* 输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
* 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。
 * 思路:把非零的先存储,为0的最后赋值
*@author:zhijm
*@Date:2020/7/18 7:57
*/
public class MoveZeroes {
    public static void main(String[] args) {
        int[]num={0,1,0,3,12};
        moveZeroes(num);
        System.out.println(Arrays.toString(num));
    }
    public static void moveZeroes(int[]nums){
        if(nums==null || nums.length==0)return;
        int index=0;
        for (int i = 0; i < nums.length;i++ ) {
            if(nums[i]!=0) nums[index++]=nums[i];
        }
        for(int i=index;i<nums.length;i++){
            nums[i]=0;
        }
    }
}
