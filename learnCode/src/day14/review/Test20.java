package day14.review;



public class Test20 {
    public static void main(String[] args) throws Exception{
        String str="中国";
        System.out.println(str.getBytes("UTF-8").length);// 一个中文3个字节
        System.out.println(str.getBytes("GBK").length);// 一个中文2个字节
        System.out.println(str.getBytes("ISO-8859-1").length);// 一个中文一个字节

        System.out.println(new String(str.getBytes("GBK"),"GBK"));
        System.out.println(new String(str.getBytes("UTF-8"),"UTF-8"));
        System.out.println(new String(str.getBytes("ISO-8859-1"),"ISO-8859-1"));
    }
}
