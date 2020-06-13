package day14.review;

import org.junit.Test;

import java.util.Arrays;

public class StringTest02 {
    @Test
    public void test(){
        //System.out.println(myTrim(" ddd gggg1  "));
       // System.out.println(reserve("123456", 1, 4));
       // System.out.println(count("1212456", "1"));

        //System.out.println(maxStr("abcwerthellolyuiodef", "cvhellolbnm"));
        System.out.println(sortStr("13456987"));
    }

    /**
     * 去掉两边的空格
     * @param str
     * @return
     */
    public static String myTrim(String str){
        char[] chars = str.toCharArray();
        int start=0;
        int end=chars.length-1;
        while(start<=end && chars[start]==' '){
            start++;
        }

        while(end>=start && chars[end]==' '){
            end--;
        }
        return str.substring(start,end+1);
    }

    //将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”

    public static String reserve(String str,int start,int end){
        char[] chars = str.toCharArray();
       for(int i=start,j=end;i<j;i++,j--){
           char  temp=chars[i];
           chars[i]=chars[j];
           chars[j]=temp;
       }
        return String.valueOf(chars);
    }

    /**
     * 获取一个字符串在另一个字符串中出现的次数。
     *       比如：获取“ ab”在 “abkkcadkabkebfkabkskab”
     *       中出现的次数
     */

    public static int count(String str1,String str2){
        int count=0;
        int index=0;
        while((index=str1.indexOf(str2,index))!=-1){
            index++;
            count++;
        }
        return count;
    }

    /**
     * 4.获取两个字符串中最大相同子串。比如：
     */

    public static String maxStr(String str1,String str2){
        String maxStr=str1.length()>str2.length()?str1:str2;
        String minStr=str1.length()>str2.length()?str2:str1;
        int len=minStr.length();
        for(int i=0;i<minStr.length();i++){
            for(int j=0,k=len-i;k<len;j++,k++){
                String string = minStr.substring(j,k+1);
                if(maxStr.contains(string)){
                    return string;
                }
            }
        }
       return null;
    }

    /**
     * 5.对字符串中字符进行自然顺序排序。
     */

    public static String sortStr(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.copyValueOf(chars);
    }
}
