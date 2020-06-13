package sort;

import java.util.Arrays;
/*
*@Description:希尔排序
*@author:zhijm
*@Date:2020/6/4 9:34
*/
public class shellSort {
    public static void main(String []args){
        int []arr={9,8,5,67,-11,1,18,15,167,-21};
        shellSort4(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int []arr){

       // int reg=arr.length/2;
       // for(int reg=arr.length/2,reg>0)


            for(int reg=arr.length/2;reg>0;reg=reg/2) {
                for (int i = reg; i < arr.length; i++) {
                    for (int j = i - reg; j >= 0; j -= reg) {
                        if (arr[j] > arr[j+reg]) {
                            arr[j] = arr[j + reg] + arr[j];
                            arr[j + reg] = arr[j] - arr[j + reg];
                            arr[j] = arr[j] - arr[j + reg];
                        }
                    }
                }
            }

        /*for(int i=2;i<arr.length;i++){  // 2=5/2
            for(int j=i-2;j>=0;j-=2){
                if(arr[j]>arr[j+2]){
                    arr[j]=arr[j+2]+arr[j];
                    arr[j+2]=arr[j]-arr[j+2];
                    arr[j]=arr[j]-arr[j+2];
                }
            }
        }
        for(int i=1;i<arr.length;i++){  // 1=2/2
            for(int j=i-1;j>=0;j-=1){
                if(arr[j]>arr[i]){
                    arr[j]=arr[j]+arr[j+1];
                    arr[j+1]=arr[j]-arr[j+1];
                    arr[j]=arr[j]-arr[j+1];
                }
            }
        }*/


    }

    public static void shellSort2(int []arr){
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                 int j=i;
                 int temp=arr[i];
                 if(arr[j]<arr[j-gap]){
                     while(j-gap>=0 && temp<arr[j-gap]){
                         //移动
                         arr[j]=arr[j-gap];
                         j-=gap;
                     }

                     // 当退出while后 就给temp找到了插入的位置
                     arr[j]=temp;
                 }
            }
        }

    }

    public static void shellSort3(int []arr){
        for(int reg=arr.length;reg>0;reg/=2){
            for(int i=reg;i<arr.length;i++){
                int j=i;
                int temp=arr[i];
                if(arr[i]<arr[j-reg]){
                    // 小于给定的值就后移一位,循环结束了 证明已经找到了位置
                    while(j-reg>=0 && temp<arr[j-reg]){
                        arr[j]=arr[j-reg];// 后移
                        j-=reg;
                    }
                    // 找到位置然后进行插入
                    arr[j]=temp;
                }
            }
        }
    }

    public static void shellSort4(int []arr){
        int temp=0;
        int index=0;
        for(int reg=arr.length/2;reg>0;reg/=2){
            for(int i=reg;i<arr.length;i++){
                temp=arr[i];
                index=i;
                if(temp<arr[index-reg]){
                    while(index-reg>=0 && temp<arr[index-reg]){
                        arr[index]=arr[index-reg];
                        index-=reg;
                    }

                    arr[index]=temp;
                }
            }
        }
    }



    public static void shellSort6(int []arr){
        int temp=0;
        int index=0;

        int length=arr.length;

        for(int reg=length/2;reg>0;reg/=2){
            for(int i=0;i<length;i++){
                temp=arr[i];
                index=i;
                if(temp<arr[index-reg]){
                    while(index-reg>=0 && temp<arr[index-reg]){
                        arr[index]=arr[index-reg];
                        index-=reg;
                    }
                    // 找到合适的插入位置
                    arr[index]=temp;
                }
            }
        }

    }
}
