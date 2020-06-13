package day14;

import org.junit.Test;

/**
 * 编写程序，在main方法中接收参数字符串” abcdjklnmuxwxyz ”，打印字符串长度；
 * 删除字符串开始和结尾处的空白，以获得新字符串，并打印输出新串的长度；
 * 判断新字符串是否以“abc”开头，是否以“xyz”结尾，打印判断结果；
 * 获取该新串第3位至第6位间的子串，将其转换为大写并打印；
 * 查找该新串是否包含“lnm ”子串，并打印子串在字符串中的位置。
 */
public class TestString {

    public static void main(String[] args) {
        System.out.println(args[0]);
        String string = args[0];
        System.out.println(string.length());

        string = string.trim();
        System.out.println(string.length());

        boolean start = string.startsWith("abc");
        System.out.println(start);

        boolean endsWith = string.endsWith("xyz");
        System.out.println(endsWith);
        String substring = string.substring(2, 5);
        System.out.println(substring.toUpperCase());

        boolean lnm = string.contains("lnm");
        System.out.println(lnm);

        int indexOf = string.indexOf("lnm");
        System.out.println(indexOf);

    }

    /**
     * 模拟一个trim方法，去除字符串两端的空格。
     *
     * 将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
     *
     * 获取一个字符串在另一个字符串中出现的次数。
     * 比如：获取“ ab”在 “abkkcadkabkebfkabkskab”
     * 中出现的次数
     */
    @Test
    public void test(){
        String string = " aabc ";
        String string1 = myTrim(string);
        System.out.println(string1);
        System.out.println(myReverse(string1, 2, 3));
        System.out.println(myReverse01(string1, 2, 3));
        System.out.println(count("ab", "abkkcadkabkebfkabkskab"));
    }

    public static int count(String indexStr,String str){
        int count=0;
        int index=0;
        while((index=str.indexOf(indexStr,index))!=-1){
            count++;
            index+=indexStr.length();
        }
        return count;
    }
    /**
     * 模拟一个trim方法，去除字符串两端的空格。
     * @param str
     * @return
     */
    public static String   myTrim(String str){
        char[] chars = str.toCharArray();
        int stat=0;
        int len=chars.length-1;
        while( stat<=len&& chars[stat]==' '){
            stat++;
        }
        while (len>=stat && chars[len]==' '){
            len--;
        }
        return str.substring(stat,len+1);
    }

    public static String myReverse(String str,int startIndex,int endIndex){

        char[] chars = str.toCharArray();
        for(int i=startIndex;i<endIndex;i++){
            char temp= chars[i];
;            chars[i]=chars[endIndex];
            chars[endIndex]=temp;
            endIndex--;

        }
        return new String(chars);
    }

    /**
     * 将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String myReverse01(String str,int start,int end){
        char[] chars = str.toCharArray();
        for(int i=start,j=end;i<j;i++,j--){
            char temp=chars[i];
            chars[i]=chars[j];
            chars[j]=temp;
        }
        return new String(chars);
    }
    @Test
    public void test01(){
        //System.out.println(findMaxStr("cvhellobnmyuiodef", "abcwerthelloyuiodef"));

        System.out.println(strSort("cabd"));
    }

    /**
     * 4.获取两个字符串中最大相同子串。比如：
     * str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
     * 提示：将短的那个串进行长度依次递减的子串与较长
     * 的串比较。
     */
    public static String findMaxStr(String str2,String str1){
        int end=str2.length()-1;
        String maxStr="";
        for(int i=0;i<end;i++){
            for(int j=i;j<end;j++){
                String str=str2.substring(i,j+1);
                if(str1.contains(str)){
                    if(maxStr.length()<str.length()) {
                        maxStr = str;
                    }
                }

            }
        }
        return maxStr;
    }



    /** 5.对字符串中字符进行自然顺序排序。
     * 提示：
     * 1）字符串变成字符数组。
     * 2）对数组排序，选择，冒泡，Arrays.sort();
     * 3）将排序后的数组变成字符串。
     */

    public static String strSort(String str){
        char[] chars = str.toCharArray();
        // 选择排序
        for(int i=0;i<chars.length-1;i++){
            for(int j=i+1;j<chars.length;j++){
                if(chars[i]>chars[j]){
                    char temp=chars[i];
                    chars[i]=chars[j];
                    chars[j]=temp;
                }
            }
        }

        boolean flag=true;
        for(int i=0;i<chars.length-1;i++){
            for(int j=0;j<chars.length-1-i;j++){
                if(chars[j]>chars[j+1]){

                }
            }
        }
        return new String(chars);
    }
}
