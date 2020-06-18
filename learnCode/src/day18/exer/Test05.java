package day18.exer;

import java.util.Arrays;
import java.util.Comparator;

public class Test05 {
    public static void main(String[] args) {
        Integer[] arr = {2,4,7,1,3};
        sort(arr);
        System.out.println(Arrays.toString(arr));

        String[] all = {"hello","Hello","Chai","chai"};
        sort(all, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(Arrays.toString(all));
    }

    public static <T extends  Comparable<T>>void sort(T[]arr){
        boolean flag=false;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if(arr[j].compareTo(arr[j+1])>0){
                        flag=true;
                        T temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
                if(!flag){
                    break;
                }else {
                    flag=true;
                }
            }
    }

    public static <T>void sort(T[] arr, Comparator<? super T> t){
          boolean flag=false;
            for(int i=0;i<arr.length-1;i++){
                for(int j=0;j<arr.length-1-i;j++){
                    if(t.compare(arr[j],arr[j+1])>0){
                        T temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                        flag=true;
                    }
                }

                if(!flag){
                    break;
                }else {
                    flag=false;
                }
            }


    }
}
