package day14.review;

public class Test05 {
    public static void main(String[] args) {
        System.out.println("随机数为:");
        double  number=Math.random()*101;
        System.out.println(number);
       String str=number+"";
       str=str.substring(0,str.indexOf(".")+3);
        System.out.println("转换为"+str);

    }
}
