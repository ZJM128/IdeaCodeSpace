package Search;

import java.util.ArrayList;
import java.util.List;
/*
*@Description:二分查找法 的前提是数组是有序的
*@author:zhijm
*@Date:2020/6/4 12:03
*/
public class BinarySearch {
    public static void main(String[]args){
        int[]arr={3,2,6,8,8,8,9};
        System.out.println("下标:"+binarySearch1(arr, 0, arr.length-1, 8));
    }

    public static int binarySearch(int[]arr,int left,int right,int value){
        int middle=(left+right)/2;
        if(left>right){
            return -1;
        }
        if(arr[middle]>value) {
            right=middle-1;
            return binarySearch(arr, left, right, value);
        }if(arr[middle]<value){
            left=middle+1;
            return binarySearch(arr,left,right,value);
        }else {
            return middle;
        }
    }

    public static List<Integer> binarySearch1(int[]arr,int left,int right,int searchValue){
        if(left>right){
            return null;
        }
        int middleNunber=(left+right)/2;
        if(arr[middleNunber]>searchValue){
            middleNunber--;
            return binarySearch1(arr,left,middleNunber,searchValue);
        }else if(arr[middleNunber]<searchValue){
            middleNunber++;
            return binarySearch1(arr,middleNunber,right,searchValue);
        }else{

            List<Integer> list = new ArrayList<>();
            list.add(middleNunber);
            // 遍历左边的看有没有相同的
            int leftTemp=middleNunber-1;
            while(true){
                if(leftTemp<0 || arr[leftTemp]!=searchValue){
                    break;
                }
                list.add(leftTemp);
                leftTemp--;

            }

            // 遍历右边的有没有相同的元素
            int rightTemp=middleNunber+1;
            while (true){
                if(rightTemp>right || arr[rightTemp]!=searchValue){
                    break;
                }
                list.add(rightTemp);
                rightTemp++;
            }
            return list;
        }

    }
}
