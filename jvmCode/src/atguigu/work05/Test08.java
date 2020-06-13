package atguigu.work05;

public class Test08 {
    public static void main(String []args){
        int[]arr={1,0,4,3,6};
        MyArrays arrays=new MyArrays();
        MyArrays.sort(arr);
        for(int i:arr){
            System.out.print(i+"\t");
        }
        System.out.println();
        int i = arrays.indexOf(arr, 5);
        System.out.println("下标是"+i);
        int[] copyArr= arrays.copy(arr, 6);
        for(int j:copyArr){
            System.out.print(j+"\t");
        }
    }
}
