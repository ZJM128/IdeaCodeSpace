package algorithm;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {

        int[] arr = kmpNext("aaa");
        System.out.println(Arrays.toString(arr) );
    }

    /**
     * kmp算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表,是子串的对应的部分匹配表
     * @return 没有匹配返回-1,匹配到返回相应的位置
     */

    public static  int kmpSearch(String str1,String str2,int[]next){
       for (int i=0,j=0;i<str1.length();i++){
           // 当不匹配的时候调整j的值
           while(j>0 && str1.charAt(i)!=str2.charAt(j)){
               j=next[j-1];
           }
           if(str1.charAt(i)==str2.charAt(j)){
               j++;
           }
           if(j==str2.length()){// 找到了
                return i-j+1;
           }
       }

        return -1;
    }

    /**
     * 获取到一个字符串的部分匹配值表
     * @param dest 字符串
     * @return
     */
    public static  int[]kmpNext(String dest){
        // 创建一个next数组保存部分匹配值
        int[]next=new int[dest.length()];
        // 如果字符串是长度1为部分匹配值就是0,匹配的是前缀和后缀是否有相同的词
        // 比如 aaab 前缀 a aa aaa  后缀 a aa aab --->匹配到的是[1,2,0]
        next[0]=0;
        // i代表后缀 j代表前缀
        for (int i =1,j=0; i < dest.length(); i++) {
            // 当dest.chatAt(i)!=dest.charAt(j) 需要从next[j-1]获取新的j
            // 直到发现有dest.charAt(i)==dest.charAt(j)成立才退出
            while (j>0 && dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];// 不满足则需要重新把j赋值
            }
            // 当dest.chatAt(i)==dest.charAt(j)满足时,部分匹配值就是+1
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return  next;
    }

}
