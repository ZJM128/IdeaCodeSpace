package Search;

import org.junit.Test;
/*
插值查找
*@Description:Ⅰ,对于数据量大.关键字分布比较均匀的查找表来说,速度更快
              Ⅱ,关键字分布不均匀的情况下,该方法不一定比折半查找要好
*@author:zhijm
*@Date:2020/6/4 13:30
*/
public class InsertSearch {

    @Test
    public void test01(){
        int[]arr=new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i]=i+1;
        }
        System.out.println("下标是"+insertSerch(arr, 0, arr.length - 1, 99));
    }


    public static int insertSerch(int[]arr,int left,int right,int searchValue){
        if(left>right || searchValue<arr[0] || searchValue>arr[right]){
            return 0;
        }

        int minddle=left+(right-left)*(searchValue-arr[left])/(arr[right]-arr[left]);
        int middleVaule=arr[minddle];
        if(middleVaule<searchValue){
            return insertSerch(arr,minddle+1,right,searchValue);
        }else if(middleVaule>searchValue){
            return insertSerch(arr,left,minddle-1,searchValue);
        }else {
            return minddle;
        }
    }
}
