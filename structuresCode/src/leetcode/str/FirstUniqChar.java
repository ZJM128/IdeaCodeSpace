package leetcode.str;

import java.util.LinkedHashMap;

/*
*@Description:给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
*@author:zhijm
*@Date:2020/7/28 0:39
*/
public class FirstUniqChar {

    public static void main(String[] args) {
        String string = "leetcode";
        System.out.println(firstUniqChar1(string));
    }
    public static int firstUniqChar(String s) {
        if(s.isEmpty())throw new RuntimeException("空字符串");
        // 把字符串变成字符数组
        char[] chars = s.toCharArray();
        // 遍历查出每个字母有多少个
          // 声明一个变量 存储字母和个数
        LinkedHashMap<Character,Integer>listMap=new LinkedHashMap<>();
        for (char word : chars) {
            if(!listMap.containsKey(word)){
                listMap.put(word,1);
            }else{
                listMap.put(word,listMap.get(word)+1);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if(listMap.get(chars[i])==1){
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqChar1(String s) {
        // 声明一个存储字符和数量的数组
         char[]temp=new char[26];
        final char[] array = s.toCharArray();
        int length=array.length;
        // 遍历字符数组得到相应字符和数量
        for (int i = 0; i < length; i++) {
            temp[array[i]-'a']++;
        }
        // 如果存储的字符和个数有等于1的则返回
        for (int i = 0; i < length; i++) {
            if(temp[array[i]-'a']==1){
                return i;
            }
        }
        return -1;

    }
}
