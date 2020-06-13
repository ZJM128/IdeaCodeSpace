package arithemetic.test;

public class MergetSort {
    public static void main(String[] args) {
        int[]arr={3,6,7,5,4,3};
        int[]temp=new int[arr.length];
        mergerSort(arr,0,arr.length-1,temp);
        for (int i : temp) {
            System.out.print(i+" ");
        }
    }
    public static void mergerSort(int[]arr,int left,int right,int[]temp){
        if(left<right){
            int middleIndex=(left+right)/2;
            // 从左边开始分组
            mergerSort(arr,left,middleIndex,temp);
            // 从右边开始分组
            mergerSort(arr,middleIndex+1,right,temp);
            // 合并
            merger(arr,left,middleIndex,right,temp);
        }
    }

    public static void merger(int[]arr,int left,int middle,int right,int[]temp){
        int i=left;
        int j=middle+1;
        int t=0;
        // 一 从中间分开把两边的数据就行对比 小的逐个放入temp中
        while(i<=middle && j<=right){
           if(arr[i]<arr[j]){
               temp[t]=arr[i];
               t++;
               i++;
           }else{
               temp[t]=arr[j];
               j++;
               t++;
           }
        }

        // 把剩余的数逐个放入中间数组
        while(i<=middle){
            temp[t]=arr[i];
            t++;
            i++;
        }
        while(j<=right){
            temp[t]=arr[j];
            t++;
            j++;
        }

        // 把中间数组复制到arr
        int tempIndex=left;
        t=0;
        while(tempIndex<=right){
            arr[tempIndex]=temp[t];
            tempIndex++;
            t++;
        }
    }
}
