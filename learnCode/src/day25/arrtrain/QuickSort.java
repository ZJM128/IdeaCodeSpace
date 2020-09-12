package day25.arrtrain;

import java.util.Arrays;

/*
*@Description:快速排序
*@author:zhijm
*@Date:2020/6/25 18:25
*/
public class QuickSort {
    public static void main(String[] args) {
        int[]arr={4,8,2,3,6,9};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static void quickSort(int[]arr){
        for (int i = 0; i < arr.length - 1; i++) {// 最后一次不用比较
            for (int j = i+1; j < arr.length; j++) {// 最后一个元素也有比完
                if(arr[i]<arr[j]){
                    arr[i]=arr[i]^arr[j];
                    arr[j]=arr[i]^arr[j];
                    arr[i]=arr[j]^arr[i];
                }
            }
        }
    }
}
