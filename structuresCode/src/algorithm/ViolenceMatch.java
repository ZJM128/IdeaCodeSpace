package algorithm;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1="你还在这里干嘛,努力的学习天天向上,天天向上,努力学习";

        String s2="努力学习";
        System.out.println(violenceMatch(s1, s2));
    }

    private  static  int violenceMatch(String s1,String s2){
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        int length1=chars.length;
        int length2=chars1.length;

        int i=0;
        int j=0;
        while (i<length1 && j<length2){
            if(chars[i]==chars1[j]){// 匹配到了就可以匹配下一个
                i++;
                j++;
            }else{// 当遇到一个不匹配了,就把j赋值为0,i赋值为i-j+1 也就是s1的 下一个再进行匹配
                i=i-j+1;
                j=0;
            }
        }
        if(length2==j){// 当 j==length2的时候证明匹配到了 则返回相对用的下标
            return i-j;
        }else {
            return -1;// 没有匹配到则返回-1
        }

    }
}
