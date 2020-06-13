package day07;

/**
 * 练习一
 * 创建一个char类型的26个元素的数组，分别 放置'A'-'Z‘。
 * 使用for循环访问所有元素并打印出来。
 * 提示：char类型数据运算 'A'+1 -> 'B'，'0'+1 -> '1‘
 * 练习二
 * 创建一个char类型的36个元素的数组，前26个元素放置'A'-'Z‘, 后10个元素放置'0'-'9‘。
 * 使用for循环访问所有元素并打印出来。
 */
public class ArrayTest {

    public static void main(String []args){
        char[] ch=new char[26];

        for(int i=0;i<ch.length;i++){
            ch[i]=(char)('A'+i);
        }

        for(int i=0;i<ch.length;i++){
            System.out.print(ch[i]+" ");
        }
        System.out.println();
        print();
    }

    /**
     * 创建一个char类型的36个元素的数组，前26个元素放置'A'-'Z‘, 后10个元素放置'0'-'9‘。
     * 使用for循环访问所有元素并打印出来。
     */
    public static void print(){
        char[] chArr=new char[36];
        for(int i=0;i<chArr.length;i++){
            if(i<26) {
                chArr[i] = (char) ('A'+i);
            }else{
             chArr[i]=(char)('0'+i-26);
            }
        }

        for(int i=0;i<chArr.length;i++){
            System.out.print(chArr[i]+" ");
        }
    }
}
