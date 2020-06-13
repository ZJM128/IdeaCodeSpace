package day14.review;

import java.util.Arrays;

public class Test11 {
    public static void main(String[] args) {
        String str="342567891";
        String s = strSort(str);
        System.out.println(s);
    }
    public static  String  strSort(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
