package day25.arrtrain;

import java.util.Arrays;

/*
*@Description:冒泡 最小的或最大的一个一个往下冒
*@author:zhijm
*@Date:2020/6/25 18:26
*/

public class ArraySort {
    public static void main(String[] args) {
        int[]arr={1,8,6,3,4,2,5};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static  void bubbleSort(int[]arr){
        boolean flag=false;
        for (int i = 0; i < arr.length-1; i++) {// 最后一次不用比了
            for (int j = 0; j < arr.length - 1 - i; j++) {// 已经最小的值不用比了,-i
                if(arr[j]<arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag=true;
                }
            }
            if(!flag){
                break;
            }else {
                flag=false;
            }
        }
    }
}
