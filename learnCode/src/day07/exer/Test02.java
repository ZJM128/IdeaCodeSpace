package day07.exer;

public class Test02 {

    public static void main(String[]args){
        int []arr=new int[20];
        intArray(arr);

        for(int number:arr){
            System.out.println(number);
        }
    }

    public static void intArray(int []arr){
        for(int i=0;i<arr.length;i++){
            arr[i]=i*2+20;
        }
    }
}
