package arithemetic.test;

public class BucketSort {

    public static void main(String[]args){
        int[]arr={2,5,65,78,44,456,21,1};
        bucketSort(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void bucketSort(int[] arr){
        int[][]buckectArray=new int[10][arr.length];
        int []indexArray=new int[10];
        int max=arr[0];
        for (int i = 0; i <arr.length ; i++) {
            if(max<arr[i]){
                max=arr[i];
            }
        }
        int lenght=(max+"").length();
        for(int i=0,n=1;i<lenght;i++,n*=10){

            for(int j=0;j<arr.length;j++){
                int temp=arr[j]/n%10;
                buckectArray[temp][indexArray[temp]++]=arr[j];
            }
            int index=0;
            for(int k=0;k<indexArray.length;k++){
                if(indexArray[k]!=0){
                    for(int j = 0;j<indexArray[k];j++){
                        arr[index++]=buckectArray[k][j];//buckectArray[k][j] 第几个桶的第几个数 取出重新放进数组中
                    }
                }
                indexArray[k]=0;
            }
        }
    }
}
