package leetcode.str;

/*
*@Description:给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。
*@author:zhijm
*@Date:2020/8/1 15:52
*/
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s){
        int leftIndex=0;
        int rightIndex=s.length()-1;
        while(leftIndex<rightIndex){
            // 从左往右找字符
            while(leftIndex<rightIndex && !Character.isLetterOrDigit(s.charAt(leftIndex))){
                leftIndex++;
            }
            // 从右往左找字符
            while (leftIndex<rightIndex && !Character.isLetterOrDigit(s.charAt(rightIndex))){
                rightIndex--;
            }

            if(leftIndex<rightIndex) {
                // 判断是否相等,不相等直接退出
                if (Character.toUpperCase(s.charAt(leftIndex)) != Character.toUpperCase(s.charAt(rightIndex))) {
                    return false;
                }
            }
            leftIndex++;
            rightIndex--;
        }
        return true;
    }
}
