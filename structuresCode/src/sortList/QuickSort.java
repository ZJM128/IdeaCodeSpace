package sortList;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[]arr={3,4,2,9,1,8,0};
        quickSort(0,arr.length-1,arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int left,int right,int[]arr){
        // 获取最左边的索引和最右边的索引
        int l=left;
        int r=right;
        // 以中间的索引为比较的基数
        int middlePoint=arr[(left+right)/2];

        // 开始遍历比较
        while(l<r){
            // 先往左遍历,找到比中间值大的
            while (arr[l]<middlePoint){
                l++;
            }
            // 往右遍历,找一个比中间值小的
            while (arr[r]>middlePoint){
                r--;
            }
           // 如果l>=r的时候就没有必要比较了,因为已经满足了以中间值为界限左边小,右边大
            if(l>=r){
                break;
            }
            // 开始交换
            int tenp=arr[l];
            arr[l]=arr[r];
            arr[r]=tenp;
            // 如果交换完成后 两值都等于中间值,那就跳过  这一步可有可无,有的话就优化一些
            if(arr[l]==middlePoint){
                r--;
            }
            if(arr[r]==middlePoint){
                l++;
            }
        }

        // 如果最后两个索引相等 ,那就各自一个往右移动一位,一个往左移动一位
        if(l==r){
            r--;
            l++;
        }
        if(l<right){
            quickSort(l,right,arr);
        }
        if(r>left){
            quickSort(left,r,arr);
        }
    }
}
