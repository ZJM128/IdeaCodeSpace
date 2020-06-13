package day08;

public class Test03 {
    public static void main(String []args){
        int [][]arr={{1,2},{1,3}};
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }

            System.out.println();
        }
        test(4);
    }

    public static void test(int ...i){
        for(int num:i){
            System.out.println(num);
        }
    }
}
