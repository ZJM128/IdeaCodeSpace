package sort;
/*
*@Description:快速排序
*@author:zhijm
*@Date:2020/6/3 14:19
*/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr={5,1,3,6,0,2};
        quickSort2(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i+" ");
        }

    }

    private static void quickSort(int[] arr, int left, int right){

        int l=left;
        int r=right;
        int pivot=arr[(left+right)/2];
        System.out.println(pivot);
        int temp;

        while(l<r){
            while(arr[l]<pivot){
                l++;
            }

            while(arr[r]>pivot){
                r--;
            }

            if(l>=r){
                break;
            }
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;

            if(arr[l]==pivot){
                r--;
            }
            if(arr[r]==pivot){
                l++;
            }
        }

        if(r==l){
            r--;
            l++;
        }

        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }
    }

    /**
     *快速排序
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort2(int []arr,int left,int right){
        int l=left;
        int r=right;
        int pivot=arr[(left+right)/2];
        int temp;

        while(l<r){
            // 从左往右进行遍历,找出左边大于piovt的值
            while(arr[l]<pivot){
                l++;
            }
            // 从右往左进行遍历,找出右边小于piovt的值
            while(arr[r]>pivot){
                r--;
            }
            //当左边的索引大于或等于了右边的索引则停止循坏,目的是后面的交换操作不用运行
            if(l>=r){
                break;
            }

            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            if(arr[l]==pivot){
                r--;
            }
            if(arr[r]==pivot){
                l++;
            }



        }
        if(l==r){
            l++;
            r--;
        }

        if(l<right){
            quickSort2(arr,l,right);
        }

        if(left<r){
            quickSort2(arr,left,r);
        }
    }
}
