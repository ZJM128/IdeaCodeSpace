package leetcode.str;

public class Atoi {

    public static void main(String[] args) {
        System.out.println(myAtoi( "21"));
    }
    public static int myAtoi(String str) {
        // 去空格
        final String s = str.trim();
        // 转为字符数组
        final char[] array = s.toCharArray();
        // 判断长度是否为0
        if(array.length==0) return 0;
        int res=0,temp=Integer.MAX_VALUE/10;
        int i=1,sing=1;
        if(array[0]=='-')sing=-1;
        else if(array[0]!='+')i=0;
        for (int j = i; j < array.length; j++) {
            if(array[j]<'0' || array[j]>'9') break;
            if(res>temp || res==temp&&array[j]>'7'){
                return sing==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            res=res*10+(array[j]-'0');
        }
        return sing*res;
    }
}
