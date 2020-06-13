package atguigu.work05;

public class MyArrays {
    public static void main(String[] args) {
        int arr[]={2,5,6,1,3,9};
        sort(arr);
     for(int i:arr){
         System.out.println(i);
     }
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void sort(int []arr){

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    public int  indexOf(int[]arr,int value){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }

    public int[] copy(int []arr,int len){
        int[] newArr=new int[len];
        for(int i=0;i<arr.length;i++){
            newArr[i]=arr[i];
        }
        return newArr;
    }

}
