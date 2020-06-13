package atguigu.reviewstrucure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SparseArr {
    public static void main(String[] args){
        int arr[][]=new int[11][11];
        arr[1][2]=1;
        arr[2][3]=1;
        arr[3][3]=1;
        arrToSparseArr(arr);
    }
    //
    public static void arrToSparseArr(int[][]arr){
        // 遍历二维数组 得到有几个有效值
        int arrRow=arr.length;
        int arrCol=arr[0].length;
        int validCount=0;
        for(int i=0;i<arrRow;i++){
            for(int j=0;j<arrCol;j++){
                if(arr[i][j]!=0){
                    validCount++;
                }
            }
        }
        // 声明稀疏数组 有效个数加一行 这一行用来存原数组的行,列,有效个数
        int[][] sparseArr=new int[validCount+1][3];
        // 初始化数组
        sparseArr[0][0]=arrRow;
        sparseArr[0][1]=arrCol;
        sparseArr[0][2]=validCount;
        int count=1;
        // 遍历原数组取出有效值 赋值给稀疏数组
        for(int i=0;i<arrRow;i++){
            for(int j=0;j<arrCol;j++){
                if(arr[i][j]!=0){
                    sparseArr[count][0]=i;//行
                    sparseArr[count][1]=j;// 列
                    sparseArr[count][2]=arr[i][j];// 值
                    count++;
                }
            }
        }
        File file = new File("D:\\SpasreArr\\SparseArr.txt");
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        int sparseArrRow = sparseArr.length;
        int spareArrCol = sparseArr[0].length;
       try {
            outputStream = new FileOutputStream(file,false);

            for (int i = 0; i < sparseArrRow; i++) {
                for (int j = 0; j < spareArrCol; j++) {
                    String str=null;
                    if(j==spareArrCol-1)
                        str=sparseArr[i][j] +"";
                    else
                        str=sparseArr[i][j] +",";
                    outputStream.write(str.getBytes());
                }
                if(i!=sparseArrRow-1)
                outputStream.write("#".getBytes());
            }

        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if(outputStream!=null)
                 outputStream.close();
            }catch (Exception e){
                System.out.println(e);
            }

        }
        try{
            inputStream=new FileInputStream(file);
            byte[]bytes=new byte[1024];
            int len=0;
            String str = null;
            while((len=inputStream.read(bytes))!=-1){
               str=new String(bytes,0,len);
            }

            final String[] split = str.split("#");
             String[] rowAndColStr = split[0].split(",");
            int row=Integer.parseInt(rowAndColStr[0]);
            int col=Integer.parseInt(rowAndColStr[1]);
            int[][]newArr=new int[row][col];
            for(int i=1;i<split.length;i++){
                rowAndColStr=split[i].split(",");
                newArr[Integer.parseInt(rowAndColStr[0])][Integer.parseInt(rowAndColStr[1])]=Integer.parseInt(rowAndColStr[2]);
            }
            for(int i=0;i<newArr.length;i++){
                for(int j=0;j<newArr[0].length;j++){
                    System.out.print(newArr[i][j]+"\t");
                }
                System.out.println();
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                if(inputStream==null){
                    inputStream.close();
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        // 稀疏数组还原数组
//        //声明数组
//        int newArr[][]=new int[sparseArr[0][0]][sparseArr[0][1]];
//        // 遍历稀疏数组
//        for(int i=1;i<sparseArrRow;i++){
//                newArr[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
//        }
//
//        System.out.println("还原后的数组");
//        for(int i=0;i<newArr.length;i++){
//            for(int j=0;j<newArr[0].length;j++){
//                System.out.print(newArr[i][j]+"\t");
//            }
//            System.out.println();
//        }
    }
}
