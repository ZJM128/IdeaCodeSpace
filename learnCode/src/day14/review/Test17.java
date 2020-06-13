package day14.review;

public class Test17 {
    String str=new String("good");
    char[]ch={'a','b','c'};

    public static void main(String[] args) {
        Test17 test17=new Test17();
        test17.change(test17.str,test17.ch);
        System.out.println(test17.str+" and");
        System.out.println(test17.ch);
    }

    public void change(String str,char[] che){
        str="stes ok";
        che[0]='g';
    }
}
