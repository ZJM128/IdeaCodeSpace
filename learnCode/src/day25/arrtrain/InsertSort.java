package day25.arrtrain;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[]arr={ 5,4,3,6,9};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static void insertSort(int[]arr){
        int middleNumber=0;
        int middleIndex=0;
        for(int i=1;i<arr.length;i++){// i为1 从第二个数开始
            middleNumber=arr[i];
            middleIndex=i-1;// 记录有序部分的最后一个数
            while(middleIndex>=0 &&middleNumber<arr[middleIndex]){//如果后一个数比前一个数大 就把前一个数赋值后一个数
                arr[middleIndex+1]=arr[middleIndex];
                middleIndex--;// 减减后不满足,则退出
            }
            if(middleIndex + 1!=i) {// 判断有没有进行数据的交换,没有也就不进行交换了
                arr[middleIndex + 1] = middleNumber;
            }


        }
    }
}
