package day08.test;

public class Test01 {

    public static void main(String []args){
    /*    int [][]arr;
        //静态初始化
        arr=new int[][]{{1,4,3},{2,5,4},{7,9}};

        //动态初始化
        int [][]arr1=new int[2][3];//{{0,0,0},{0,0,0}}


        // 方式二
        int [][]arr3=new int[2][] ;//{null,null}
        arr3[0]=new int[3];//{{0,0,0},null}
        arr3[1]=new int[2];//{{0,0,0},{0,0}}

        for(int i=0;i<arr3.length;i++){
            for(int j=0;j<arr3[i].length;j++){
                System.out.print(arr3[i][j]+" ");
            }
            System.out.println();
        }


        for(int[]arr4:arr3){
            for(int num:arr4){
                System.out.print(num+" ");
            }
            System.out.println();
        }*/

        int[] a,b[];
        a=new int[2];
        b=new int[3][4];

        for(int i:a){
            System.out.print(i+" ");
        }

        System.out.println();
        for(int[] num:b){
            for(int num2:num){
                System.out.print(num2);
            }
            System.out.println();
        }


    }
}
