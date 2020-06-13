package day07.work;

public class ArrayTest01 {

    public static void main(String []args){
        int []arr=new int [20];
        for(int number:arr){
            System.out.println(number);
        }
        // 默认都是-
        intArray(arr);
        for(int num:arr){
            System.out.print(num+" " );
        }

    }
    public static void intArray(int []arr){
        for(int i=0;i<arr.length;i++){
            arr[i]=i*2+20;
        }
    }
}
