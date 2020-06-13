package day10.warkbooks;

public class BubbleSort {
    public static void main(String[] args) {
        int[]arr={10,12,96,35,46,87};
        boolean flag=false;
        for(int i=0;i<arr.length-1;i++){
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j]<arr[j+1]){
                    arr[j]=arr[j]^arr[j+1];
                    arr[j+1]=arr[j]^arr[j+1];
                    arr[j]=arr[j+1]^arr[j];
                    flag=true;
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;
            }
        }

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
