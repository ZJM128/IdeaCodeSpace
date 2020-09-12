package leetcode.array;

import java.util.Arrays;

/*
*@Description:给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。
*@author:zhijm
*@Date:2020/7/17 8:26
*/
public class PlusOne {
    public static void main(String[] args) {

        int[]arr={9,8,7,6,5,4,3,2,1,0};
        System.out.println(Arrays.toString(plusOne(arr)));
    }
    public static int[]plusOne(int[]digits){
       if(digits==null || digits.length==0)return digits;
       int num=1;
       for(int i=digits.length-1;i>=0;i--){
           int temp=digits[i]+num;
           digits[i]=temp%10;
           num=temp/10;
       }
       if(num==1){
           int[]arr=new int[digits.length+1];
           arr[0]=1;
         System.arraycopy(digits,0,arr,1,digits.length);
         return arr;
       }

        return digits;
    }
}
