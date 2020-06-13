package sort;

/*
*@Description:归并排序
*@author:zhijm
*@Date:2020/6/4 9:34
*/
public class MergetSort {
    public static void main(String[] args) {
        int[]arr={1,5,2,3};
        int[]temp=new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        for (int i : temp) {
            System.out.print(i+" ");
        }
    }

    public static void mergeSort(int[]arr,int left,int rigth,int[]temp){
        if(left<rigth){
            int middle=(left+rigth)/2;
            // 左边分开
            mergeSort(arr,left,middle,temp);
            // 右边分开
            mergeSort(arr,middle+1,rigth,temp);
            // 逐步合并
            merge(arr,left,middle,rigth,temp);
        }
    }

    /**
     * 数组合并的方法
     * @param arr  数组
     * @param left 左边索引
     * @param mid 数值中间的索引
     * @param rigth 右边的索引
     * @param temp 辅助数组,用于存储有序的数据
     */
    public static void merge(int[]arr,int left,int mid,int rigth,int []temp){
        int i=left;// 初始化1,从左边有序序列的初始索引
        int j=mid+1;// 初始化j,右边有序序列的初始索引
        int t=0;//指向temp数组的当前索引

        //(一)
        // 先把左右两边(有序)的数据按照规则填充到temp数组中
        // 直到左右两边的有序序列,又一边处理完毕为止
        while (i<=mid && j<=rigth){
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
        //(二)
        // 把左边或右边剩余的数据的一边的数据依次全部填充到temp
       while(i<=mid){
           temp[t]=arr[i];
           i++;
           t++;
       }
       while(j<=rigth){
           temp[t]=arr[j];
           j++;
           t++;
       }
        //(三)
        // 把temp数组的元素拷贝到arr
        t=0;
        int tempIndex=left;
        while(tempIndex<=rigth){
            arr[tempIndex]=temp[t];
            tempIndex++;
            t++;
        }

    }
}
