package day14.review;

public class Test16 {
    private static void change(String str,StringBuffer sb){
        str="aaaaa";
        sb.setLength(0);
        sb.append("aaaaaaaa");
    }
    public static void main(String[] args) {
        String str="b";
        StringBuffer sb=new StringBuffer("b");
        change(str,sb);
        System.out.println(str+sb);
    }
}
