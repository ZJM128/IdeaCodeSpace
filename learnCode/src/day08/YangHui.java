package day08;

/**
 * 使用二维数组打印一个 10 行杨辉三角.
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * 1 5 10 10 5 1
 * ....
 *
 * 【提示】
 * 1. 第一行有 1 个元素 第 n 行有 n 个元素
 * 2. 每一行的第一个元素和最后一个元素都是 1
 * 3. 从第三行开始 对于非第一个元素和最后一个元素的元素.
 * yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
 */
public class YangHui {
    public static void main(String []args){
        printYangHui(20);
    }

    private static void printYangHui(int n) {
        int[][]arr=new int[n][];
        for(int i=0;i<arr.length;i++){
            arr[i]=new int[i+1];
            arr[i][0]=1;
            arr[i][i]=1;
            // 最后一位已经被赋值了
            if(i>1) {
                for (int j = 1; j < i; j++) {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }
        }

        for(int [] as:arr){
            for(int num:as){
                System.out.print(num+"\t");
            }
            System.out.println();
        }
    }
}
