package arithemetic.test;

import java.util.Arrays;

public class FibonacciSearch {
    private static int MAX_SIZE=20;

    public static void main(String[] args) {
        int[]arr={1,4,6,89,90,456};
        System.out.println("下标"+fibonacciSearch(arr, 456));
    }
    //先定构建一个斐波那契数组
    private static int[] fib(){
        int[]arr=new int[MAX_SIZE];
        arr[0]=1;
        arr[1]=1;
        for (int i = 2; i < arr.length; i++) {
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr;
    }

    private static int fibonacciSearch(int[]arr,int key){
        int k=0;
        int low=0;
        int high=arr.length-1;
        int mid=0;
        int []f=fib();
        while(high>f[k]-1){
            k++;
        }
        int[] temp= Arrays.copyOf(arr,f[k]);
        for(int i=high+1;i<temp.length;i++){
            temp[i]=arr[high];
        }

        while(low<=high){
            mid=low+f[k-1]-1;

            if(key<temp[mid]){
                high=mid-1;
                k--;

            }else if(key>temp[mid]){
                low=mid+1;
                k-=2;
            }else{
                if(mid<=high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;


    }
}
