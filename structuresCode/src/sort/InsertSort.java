package sort;

import java.util.Arrays;
/*
*@Description:插入排序
*@author:zhijm
*@Date:2020/6/4 9:33
*/
public class InsertSort {
    public static void main(String []args){
        int[] arr={5,2,3,9,6};
        //insertSort(arr);

        //insertSort3(arr);
        insertSort5(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int []arr){
        /**
         * 5,2,3,9,6
         * 插入排序的思路
         * ①把数据分成两部分,一部分是有序的,一部分是无序的 即[5] [2,3,9,6]
         * ②然后从无序部分依次拿出一个元素,把这个元素存储在一个变量值中,和有序的最后一个元素进行比较,如果比有序部分的数据还大,
         * 那就暂时把这个大的值,赋值给无序部分要插入的那个数
         * 则和有序部分的倒数第二个进行比较(当然如果有序部分只有一个元素,那就值比较一次),直到找到一个比自己小的数然后在其后面进行插入
         * ③如果和有序部分的数据比较完都没有符合的就在有序部分的最后进行插入
         * ④这时有序的元素就多了一个,无序就少了一个
         * 示例
         * 5,2,3,9,6
         * ①[5],[2,3,9,6]
         * ②[2,5][3,9,6]
         * ③[2,3,5] [9,6]
         * ④[2,3,5,6][9]-->2,3,5,6,9
         * 通过示例发现n个数 通过n-1次就可以排序完成
         */

        int middleNumber=0;
        int middleIndex=0;
        for(int i=1;i<arr.length;i++){
            middleNumber=arr[i];
            middleIndex=i-1;
            while(middleIndex>=0 &&middleNumber<arr[middleIndex]){
                arr[i]=arr[middleIndex];
                middleIndex--;
            }

             if(middleIndex + 1!=i) {
                 arr[middleIndex + 1] = middleNumber;
             }


        }
    }

    public static void insertSort2(int []arr){

        int middleNumber=0;
        int middleIndex=0;

        for(int i=1;i<arr.length;i++){
             middleNumber=arr[i];
             middleIndex=i-1;
             while(middleIndex>=0 && middleNumber<arr[middleIndex]){
                    arr[i]=arr[middleIndex];
                    middleIndex--;
             }

             if(middleIndex+1!=i){
                 arr[middleIndex+1]=middleNumber;
             }

        }

    }

    public static void insertSort3(int []arr){

        int middleNum=0;
        int middleIndex=0;
        for(int i=1;i<arr.length;i++){

            middleNum=arr[i];
            middleIndex=i-1;
            while(middleIndex>=0 && middleNum<arr[middleIndex]){
                arr[i]=arr[middleIndex];
                middleIndex--;
            }

            if(middleIndex+1!=i){
                arr[middleIndex+1]=middleNum;
            }
        }
    }

    public static void insertSort4(int []arr){
        int middleN=0;
        int middleI=0;
        for(int i=1;i<arr.length;i++){
            middleN=arr[i];
            middleI=i-1;
            while(middleI>=0 && middleN<arr[middleI] ){
                arr[i]=arr[middleI];
                middleI--;
            }
            if(middleI+1!=i){
                arr[middleI+1]=middleN;
            }
        }
    }

    public static void insertSort5(int []arr){
         int num=0;
         int index=0;
         for(int i=1;i<arr.length;i++){
             num=arr[i];
             index=i-1;
             while(index>=0 && num<arr[index]){
                 arr[i]=arr[index];
                 index--;
             }

             if(index+1!=i){
                 arr[index+1]=num;
             }
         }
    }

}
