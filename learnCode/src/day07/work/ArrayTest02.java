package day07.work;

public class ArrayTest02 {
    public static void main(String[]args){
        int []arr=new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)Math.round(Math.random()*10);
        }
        for(int number:arr){
            System.out.print(number+" ");
        }

        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            if(max<arr[i]){
                max=arr[i];
            }
        }

        System.out.println("最大值"+max);

        int min=arr[0];
        for(int i=1;i<arr.length;i++){
            if(min>arr[i]){
                min=arr[i];
            }
        }
        System.out.println("最小值"+min);

        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        System.out.println("平均值"+sum/arr.length);

        System.out.println("和"+sum);
    }
}
