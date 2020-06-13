package day08.test;

public class ArrayTest02 {
    public static void main(String[]args){
        int[]arr={2,3,5,7,11,13,17,19};
        int[]arr2;
        for(int num:arr){
            System.out.print(num+" ");
        }
        System.out.println();
        // 传的是地址值
        arr2=arr;
        for(int i=0;i<arr2.length;i++){
            if(i%2==0){
                arr[i]=i;
            }
        }

        for(int num:arr){
            System.out.print(num+" ");
        }

        System.out.println();
    }
}
