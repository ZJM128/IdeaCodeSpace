package day07;

public class YangHui {
    public static void main(String []args){
        int[][]arr=new int [10][];
        for(int i=0;i<10;i++){
            arr[i]=new int[i+1];
        }
        arr[0][0]=1;
        arr[1][0]=1;
        arr[1][1]=1;
        for(int i=2;i<10;i++){
            for(int j=0;j<i;j++){
                if(j==0){
                    arr[i][0]=1;
                    arr[i][i]=1;
                }else{
                    arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
                }

            }
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
