package sort;
/*
*@Description:基数排序
*@author:zhijm
*@Date:2020/6/4 7:52
*/
public class BucketSort {
    public static void main(String[] args) {
        int[]arr={3,567,5,4,90,76};
        buckSort(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void buckSort(int[]arr){
        //创建一个二维数组用于存储排序时每个数
        int[][]bucketArray=new int[10][arr.length];// 自然数0-9,但是每个桶的个数不确定 所以只能取最大,空间换时间 0代表数位0
        int[]indexArray=new int[10];
        int max=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        int length=(max+"").length();
        for(int j=0, n = 1;j<length;j++,n*=10) {
            for (int i = 0; i < arr.length; i++) {
                // 取个位下一个,取十位,类似去下去...
                int temp = arr[i] / n % 10;
                bucketArray[temp][indexArray[temp]++] = arr[i];

            }
            int index=0;
            for(int k=0;k<indexArray.length;k++){
                if(indexArray[k]!=0) {
                    for (int i = 0; i < indexArray[k]; i++) {
                        arr[index] = bucketArray[k][i];
                        index++;
                    }
                }
                indexArray[k]=0;//清零让下一次进行统计
            }
        }
    }
}
