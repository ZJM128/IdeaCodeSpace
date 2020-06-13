package atguigu.structure;

import java.util.Scanner;

public class SparseArr {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        sc.next().charAt(0);
        int[][]arr=new int[11][11];
        arr[1][3]=1;
        arr[2][4]=2;
        arrToSparse(arr);
    }

    private static void arrToSparse(int[][]arrs){
        // 原来的数组的模式
        int row= arrs.length;
        int col=arrs[0].length;
        for(int i=0;i<row;i++){
            for (int j = 0; j < arrs[i].length; j++) {
                System.out.print("\t"+arrs[i][j]);
            }
            System.out.println();
        }
        // 先遍历原数组得出有多少个有值的数据的个数
        int sum=0;
        for(int arr[]:arrs){
            for(int i:arr){
                if(i!=0){
                    sum++;
                }
            }
        }
       //System.out.println(sum);

        // 声明稀疏数组
        int [][]sparseArr=new int[sum+1][3];
        //初始化稀疏数组的第一行
        sparseArr[0][0]=row;
        sparseArr[0][1]=col;
        sparseArr[0][2]=sum;

        int spareRow=sparseArr.length;
        int spareCol=sparseArr[0].length;

        int count=1;
        // 对稀疏数组进行复制
        for(int i=0;i<row;i++){
            for (int j = 0; j <arrs[i].length ; j++) {
                if(arrs[i][j]!=0){
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=arrs[i][j];
                    count++;

                }
            }
        }

        // 遍历稀疏数组
        System.out.println("遍历稀疏数组");
        for (int i = 0; i <spareRow ; i++) {
            for (int j = 0; j < spareCol; j++) {
                System.out.print("\t"+sparseArr[i][j]);
            }
            System.out.println();
        }


        // IO的操作

        // 稀疏数组还原原始数组

        // 先定义原始数组
        int [][]arrNew=new int [sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <spareRow ; i++) {
           arrNew[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        System.out.println("翻译后的数组");
        int row1= arrNew.length;
        int col1=arrNew[0].length;
        for(int i=0;i<row1;i++){
            for (int j = 0; j < col1; j++) {
                System.out.print("\t"+arrNew[i][j]);
            }
            System.out.println();
        }
    }
}
