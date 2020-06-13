package day14.review;

public class Test18 {
    public static void main(String[] args) {
        StringBuffer sb1=new StringBuffer("A");
        StringBuffer sb2=new StringBuffer("B");
        operate(sb1,sb2);
        System.out.println(sb1+","+sb2);
    }
    public static  void operate(StringBuffer x,StringBuffer y){
        x.append(y);
        y=x;
    }
}
