package sort;

import java.util.Arrays;
/*
*@Description:选择排序
*@author:zhijm
*@Date:2020/6/4 9:34
*/
public class  SelectSort {
    public static void main(String[] args){
//        int []arr2=new int[80000];
//        for(int i=0;i<arr2.length;i++){
//            arr2[i]=(int)(Math.random()*80000);
//        }
        int []arr={2, 1, 1,4, 3,-1,9};
//        Date date=new Date();
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        String str1=simpleDateFormat.format(date);
//        System.out.println(str1);
//        selectSort2(arr2);
//        Date date2=new Date();
//        String str2=simpleDateFormat.format(date2);
        selectSort8(arr);
        //System.out.println(str2);
        //System.out.println(Arrays.toString(arr));

       // selectSort2(arr);
       System.out.println(Arrays.toString(arr));

    }

    public static void selectSort(int []arr){
        /**
         *示例:2 ,1, 4, 3
         * ①先假设第一个数最小的(2),然后拿这个数去和后面的n个数依次进行比较,
         * ②如果发现有比最小值还小的数 则通过中间变量记录下下标和值,
         * ③然后继续拿这个最小值往后进行比较,如果又发现比最小值还小的值 就执行第②步,直到比完最后一个元素
         * ④最后把存在中间变量的最小值和最小值的下标 和假设的那个最小值进行交换
         * 示例过程:
         * 2, 1, 4, 3
         * ①2和 1, 4 ,3比较 ,发现有1比2小, 则用中间变量记录1和下标1,  然后再用存在中间变量的最小值和4比较, 不符合,再和3比较, 不符合,最后得到1为最小值,所以2和1就行位置的交换
         * 1, 2, 4, 3
         * ②2和 4 ,3 进行比较 规则和①一样
         * 1, 2, 4, 3
         * ③4和3进行比较 规则和①一样
         * 1, 2, 3 ,4
         * 通过示例演示发现 n个数 比较n-1次
         */
        int minNumber=0;
        int minIndex=0;
        for(int i=0;i<arr.length-1;i++){
            // 声明中间值用进行比较和存最小值
            minNumber=arr[i];
            minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(minNumber>arr[j]){
                    minNumber=arr[j];
                    minIndex=j;
                }
            }
            arr[minIndex]=arr[i];
            arr[i]=minNumber;
        }

    }

    public static void selectSort8(int []arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }

    /**
     * 通过观察示例交换的规律发现 当假设成立的时候 就不用进行数据的交换了,因此当中间变量的下标值和假设的值相等时,就不用就行数据的交互
     * @param arr
     */
    public static void selectSort2(int []arr){
        int minNumber=0;
        int minIndex=0;
        for(int i=0;i<arr.length-1;i++){
            minNumber=arr[i];
            minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(minNumber>arr[j]){
                    minNumber=arr[j];
                    minIndex=j;
                }
            }

            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=minNumber;
            }
        }
    }

    public static void selectSort3(int []arr){
        int minNum=0;
        int minIndex=0;
        for(int i=0;i<arr.length-1;i++){
            minNum=arr[i];
            minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(minNum>arr[j]){
                    minNum=arr[j];
                    minIndex=j;
                }
            }

            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=minNum;
            }
        }

    }

    public static void selectSort4(int []arr){
        int minNumber=0;
        int minIndex=0;

        for(int i=0;i<arr.length-1;i++){
            minNumber=arr[i];
            minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(minNumber>arr[j]){
                    minNumber=arr[j];
                    minIndex=j;
                }
            }

            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=minNumber;
            }
        }

    }

    public static void selectSort5(int []arr){
        int minNumber=0;
        int minIndex=0;
        for(int i=0;i<arr.length-1;i++){
            minNumber=arr[i];
            minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(minNumber>arr[j]){
                    minNumber=arr[j];
                    minIndex=j;
                }
            }

            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=minNumber;
            }
        }
    }
}
