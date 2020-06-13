package day08.test;

public class Test02 {

    public static void main(String[]args){

        int [] array={2,3,6,8,5,4};

        // 最大值
        int max=array[0];
        for(int i=0;i<array.length;i++){
            if(max<array[i]){
                max=array[i];
            }
        }

        System.out.println("最大值"+max);

        // 最小值
        int min=array[0];
        for(int j=0;j<array.length;j++){
            if(min>array[j]){
                min=array[j];
            }
        }

        int sum=0;
        for(int i=0;i<array.length;i++){
            sum+=i;
        }

        System.out.println("总和"+sum);

        System.out.println("平均值"+sum/array.length);

        // 数组的复制
        int[] arr2=new int[array.length];
        for(int i=0;i<array.length;i++){
            arr2[i]=array[i];

        }

        for(int num:arr2){
            System.out.print(num+" ");
        }
        System.out.println();

        // 数组的反转
        for(int i=0;i<array.length/2;i++){
            array[i]=array[i]^array[array.length-1-i];
            array[array.length-1-i]=array[array.length-1-i]^array[i];
            array[i]=array[array.length-1-i]^array[i];
        }

        for(int num:array){
            System.out.print(num+" ");
        }



    }
}
