package leetcode.str;
/*
*@Description:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转
*@author:zhijm
*@Date:2020/7/23 17:06
*/
public class ReverserInt {
    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }
    public static int reverse(int x){
        String line=x+"";
        final String[] words = line.split("");
        int i=0;
        int j=words.length-1;
        if(x<0){
            i=1;
        }
        while(i<j){
            String temp=words[i];
            words[i]=words[j];
            words[j]=temp;
            i++;
            j--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            stringBuilder.append(word);
        }
        try {
            final int number = Integer.parseInt(stringBuilder.toString());
            return number;
        }catch (Exception e){
            return 0;
        }
    }
}
