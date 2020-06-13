package day14.review;

import java.util.Arrays;

public class Test06 {
    public static void main(String[] args) {
        String[]arrStr={"banf","123456","aaff","ddddddd","ererjerr","1223"};
        System.out.println("源字符串:");
        System.out.println(Arrays.toString(arrStr));
        System.out.println();
        int len=arrStr.length;
        for (int i = 0; i < len; i++) {
            if(arrStr[i].length()>5){
                int index=0;
                for(int j=i;j<arrStr.length-1;j++){
                    String str=arrStr[j];
                    arrStr[j]=arrStr[j+1];
                    arrStr[j+1]=str;
                    index=j;
                }
                arrStr[index+1]=null;
                len--;
            }
        }
        String[]arrStr1=new String[len];
        for(int i=0;i<len;i++){
            arrStr1[i]=arrStr[i];
        }
        arrStr=arrStr1;
        System.out.println(Arrays.toString(arrStr));
    }
}
