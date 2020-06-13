package day07.work;

public class TestArray {
    public static void main(String []args){
        int []array1={2,3,5,7,11,13,17,19};
        /*int []array2=array1;

        for(int i=0;i<array2.length;i++){
            if(i%2==0){
                array2[i]=i;
            }
        }
        for(int a:array1){
            System.out.println(a);
        }
        // array2和array1 都指向同一个内存对象*/
        int []array2=new int[array1.length];
        for(int i=0;i<array1.length;i++){
            array2[i]=array1[i];
        }

        for(int n:array1){
            System.out.print(n+" ");
        }
        System.out.println();
        for(int n:array2){
            System.out.print(n+" ");
        }

    }
}
