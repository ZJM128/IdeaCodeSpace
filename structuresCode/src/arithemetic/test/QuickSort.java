package arithemetic.test;

public class QuickSort {
    public static void main(String[] args) {
        int[]arr={3,6,4,3,7,9};
        quickSort01(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void quickSort(int arr[], int left,int right){
        int l=left;
        int r=right;
        int minddleNumber=arr[(left+right)/2];
        int temp;
        while(l<r){
            while(arr[l]<minddleNumber){
                l++;
            }
            while (arr[r]>minddleNumber){
                r--;
            }

            if(l>=r){
                break;
            }
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;

            if(arr[l]==temp){
                r--;
            }
            if(arr[r]==temp){
                l++;
            }

        }

        if(l==r){
            l++;
            r--;
        }
        if(l<right){
            quickSort(arr,l,right);
        }

        if(r>left){
            quickSort(arr,left,r);
        }

    }

    public static void quickSort01(int[]arr,int left,int right){
        int l=left;
        int r=right;
        int middleNumber=arr[(left+right)/2];
        int temp;
        while(l<r){
            // 从左到右找到一个比中间的值的大的值
            while(arr[l]<middleNumber){
                l++;
            }
            // 从右到左找到一个比中间值小的值
            while(arr[r]>middleNumber){
                r--;
            }
            // 当从左边找到和从右边找重合了就终结了寻找,不终止后面的交换还会继续
            if(l>=r){
                break;
            }
            // 把左边大于中间值的值和右边大于中间值的值进行交换
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            //  两个数进行交换后 判断一下这个数是否和中间值一样,一样的话下一个数据在进行比较
            if(arr[l]==temp){
                r--;
            }
            if(arr[r]==temp){
                l++;
            }
        }
        if(r==l){
            r--;
            l++;
        }
        if(l<right){
            quickSort01(arr,l,right);
        }

        if(r>left){
            quickSort01(arr,left,r);
        }


    }
}
