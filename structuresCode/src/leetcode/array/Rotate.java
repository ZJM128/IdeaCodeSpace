package leetcode.array;
/*
*@Description:给定一个 n × n 的二维矩阵表示一个图像。
将图像顺时针旋转 90 度。
说明：
你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
* 给定 matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],
原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
*@author:zhijm
*@Date:2020/7/20 8:02
*/
public class Rotate {
    public static void main(String[] args) {
        int[][]matrix={
                {5, 1, 9,11},
                { 2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }
    public static void rotate(int[][]matrix){
        //判断矩阵是否为空
        if(matrix==null) return;
        int length=matrix.length;
        // 先翻转矩阵
        /*1	4	7
        2	5	8
        3	6	9*/
        for (int i = 0; i <length ; i++) {
            for(int j=i;j<length;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        // 然后反转每一行
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length/2; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[i][length-1-j];
                matrix[i][length-1-j]=temp;
            }
        }

    }
}
