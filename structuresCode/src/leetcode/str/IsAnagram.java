package leetcode.str;

/*
*@Description:给定两个字符串 s 和 t ，
* 编写一个函数来判断 t 是否是 s 的字母异位词。
*@author:zhijm
*@Date:2020/7/29 8:14
*/
public class IsAnagram {
    public static void main(String[] args) {
            String s="rat";
            String t="rat";
        System.out.println(isAnagram(s, t));
    }
    public static boolean isAnagram(String s,String t){
        if(s.length()!=t.length())return false;
       int[]arr=new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)-'a']++;
            arr[t.charAt(i)-'a']--;
        }
        for (int i : arr) {
            if(i!=0){
                return false;
            }
        }
       return true;
    }
}
