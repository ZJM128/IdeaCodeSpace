package sort;

import java.util.Arrays;
/*
*@Description:冒泡排序
*@author:zhijm
*@Date:2020/6/4 9:32
*/
public class BubbleSort {
    public static void main(String []args){
        int []arr={1,8,6,3,5,1};
        //bubbleSort(arr);
        //bubbleSort1(arr);
        bubbleSort6(arr);
        System.out.println(Arrays.toString(arr));

    }


    /**
     * 第一版 每次定义中间变量
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        // 原则:两两数据进行比较
        // 第一趟求出最大的
        // 第二趟求出第二大的
        //...
        // 最后一趟求出倒数第二大的
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    /**
     * 第二版 不用中间变量
     * @param arr
     */
    public static void bubbleSort1(int []arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    arr[j]=arr[j]+arr[j+1];
                    arr[j+1]=arr[j]-arr[j+1];
                    arr[j]=arr[j]-arr[j+1];
                }
            }
        }
    }

    /**
     * 用一个标志符来控制如果没有数据进行交换就结束循环
     * @param arr
     */
    public static void bubbleSor2(int []arr){
        boolean flag=false;// 定义一个标志符,如果没有进行两两交换就终止循环
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]) {
                    flag = true;
                    arr[j] = arr[j] + arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;
            }
        }
    }


    public static void bubbleSor3(int []arr){
        boolean flag=false;
         for(int i=0;i<arr.length-1;i++){
             for(int j=0;j<arr.length-1;j++){
                 if(arr[j]>arr[j+1]){
                     arr[j]=arr[j]+arr[j+1];
                     arr[j+1]=arr[j]-arr[j+1];
                     arr[j]=arr[j]-arr[j+1];
                     flag=true;
                 }
             }
             if(!flag){
                 break;
             }else{
              flag=false;
             }
         }
    }

    public static void bubbleSort4(int []arr){
        boolean flag=false;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]){
                    arr[j]=arr[j]+arr[j+1];
                    arr[j+1]=arr[j]-arr[j+1];
                    arr[j]=arr[j]-arr[j+1];
                    flag=true;
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;
            }

        }
    }

    public static void bubbleSort6(int []arr){
        boolean flag=false;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]) {
                    arr[j] = arr[j] + arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];
                    flag = true;
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;
            }
        }
    }
}
