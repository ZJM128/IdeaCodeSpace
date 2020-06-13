package day08.test;

public class YangHui {
    public static void main(String[]args){
        int[][]arr=new int[10][];
        for(int i=0;i<arr.length;i++){
            arr[i]=new int[i+1];
            arr[i][0]=1;
            arr[i][i]=1;
            if(i>1){
                for(int j=1;j<arr[i].length-1;j++){
                    arr[i][j]=arr[i-1][j]+arr[i-1][j-1];
                }
            }
        }

        for(int[]num:arr){
            for(int nun1:num){
                System.out.print(nun1+"\t");
            }
            System.out.println();
        }

    }
}
