package arithemetic.test;

import java.util.Arrays;
/*
*@Description:给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
*@author:zhijm
*@Date:2020/7/13 7:11
*/
public class Rotate {
    public static void main(String[] args) {
       int[]nums={-1,-100,3,99,89};
        //rotate(nums,3);
       // System.out.println(Arrays.toString(nums));
        rotate1(nums,2);
        System.out.println(Arrays.toString(nums));
    }
    private static void rotate(int[]nums,int k){
        int length=nums.length;
        int temp=0;// 中间变量 用于存储被替换的元素
        // 移动k个 循环k次
        for(int i=0; i<k;i++){
            temp=nums[length-1];// 记录末尾的数据
            System.arraycopy(nums, 0, nums, 1, length - 1);
            nums[0]=temp;
            //System.out.println(Arrays.toString(nums));
        }

    }

    public static void rotate1(int[] nums, int k) {
        if (k >= nums.length)
            k = k % nums.length;
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = nums[i];
        }
        for (int i = nums.length - 1; i >= k; i--) {
            nums[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }
    }
    public static void test01(){
        int[]nums={-1,-100,3,99,89};
        int[]arr=new int[nums.length];
        System.arraycopy(nums,0,arr,1,nums.length-2);
        System.out.println(Arrays.toString(arr));
    }
}
