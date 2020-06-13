package day08;

/**
 * 数据类型[]数组名=new 数据类型[]{5,1,2};
 * ①默认的和类中的默认值一样
 * arr.length
 */
public class morningWork {
    public static void main(String []args){
        int []arr={98,78,15,77,45};

        int length=arr.length;
        int min=arr[0];
        int index=0;
        for(int i=0;i<arr.length;i++){
            if(min>arr[i]){
                min=arr[i];
                index=i;
            }
        }
        System.out.println("最小值为="+min+" 下标为="+index);

        int max=arr[0];
        for(int j=0;j<length;j++){
            if(max<arr[j]){
                max=arr[j];
                index=j;
            }
        }
        System.out.println("最大值为="+min+" 下标为="+index);
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        System.out.println("总和"+sum);
        System.out.println("平均值"+sum/arr.length);




        System.out.println();
        int[] arr2=new int[length];
        // 物理上的
         for(int i=length-1;i>=0;i--){
             arr2[length-1-i]=arr[i];
         }
        for(int num:arr2){
            System.out.print(num+"\t");
        }
        System.out.println();
        System.out.println("=========================");
        for(int i=0;i<length/2;i++){
            arr[i]=arr[i]+arr[length-1-i];
            arr[length-1-i]=arr[i]- arr[length-1-i];
            arr[i]=arr[i]- arr[length-1-i];
        }

        for(int num:arr){
            System.out.print(num+"\t");
        }

        System.out.println();
        System.out.println("=========================");
        for(int i=0;i<length/2;i++){
            arr[i]=arr[i]^arr[length-1-i];
            arr[length-1-i]=arr[i]^arr[length-1-i];
            arr[i]=arr[length-1-i]^arr[i];
        }

        for(int num:arr){
            System.out.print(num+"\t");
        }


    }
}
