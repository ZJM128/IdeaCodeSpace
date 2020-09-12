package Search;





import org.junit.Test;

import java.util.Arrays;
/*
*@Description:斐波那契数列,数组必须是有序的
*@author:zhijm
*@Date:2020/6/4 21:22
*/
public class FibonacciSearch {
    private static int MAX_SIZE=20;
    @Test
    public void test(){
        int[]arr={4,7,34,67,90,103};
        System.out.println("下标:"+fibonacciSearch(arr, 103));
    }


    //构建斐波那契数列
    private static int[] fib (){
        int[]arr=new int[MAX_SIZE];
        arr[0]=1;
        arr[1]=1;
        for(int i=2;i<arr.length;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr;
    }

    private static int fibonacciSearch(int[]arr,int key){
        int mid=0;
        int k=0;// 表示斐波那契分割数值的下标
        int low=0;
        int high=arr.length-1;
        int[]f=fib();
        // 获取到斐波那契额分割的下标
        while(high>f[k]-1){
            k++;
        }
        // 因为f[k] 值可能大于arr的长度,因此需要使用Arrays构建一个新的数组,并指向arr
        int[] temp=Arrays.copyOf(arr,f[k]);

        // 实际上需求使用arr数组最后的数值填充temp
        for(int i=high+1;i<temp.length;i++){
            temp[i]=arr[high];
        }

        //使用while来循环处理,找到我们的数key
        while(low<=high){
            mid=low+f[k-1]-1;
            if(key<temp[mid]){//0 1 2 4  1<2 往左
                high=mid-1;
                k--;
            }if(key>temp[mid]){// 1 2 3 4 6  4>3 往右
                low=mid+1;
                k-=2;
            }else{
                //  返回较小的值
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
