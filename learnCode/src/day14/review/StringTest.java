package day14.review;

import org.junit.Test;

public class StringTest {
    @Test
     public void test01(){
        System.out.println("============");
         String str1="12";
         String str2="12";
        System.out.println(str1==str2);// true
        String str3=new String("12");
        System.out.println(str1==str3);// false str1指向常量池的地址,str3是堆中的地址
        String str4=new String("12");
        System.out.println(str3==str4);// false 不同堆空间的地址

        String str5="1212";
        String str6=str1+str2;//通过StringBuilder构造了1212
         str6=str6.intern();
        System.out.println("str5==str6:"+(str5==str6));// false

        String str7=str1+"12";// 也是通过StringBuilder进行的构建1212
        System.out.println(str5==str7);//false

        String str8="12"+"12";// 直接在常量池中构建的1212,因为常量池中只有一个1212
        System.out.println(str5==str8);

     }

     @Test
     public void test02(){
         String str1="  aBc";
         String str2="hh\t\t\t";
         String trim = str1.trim();
         System.out.println(trim);
         System.out.println(str2.trim());

         String str3="123456";
         String substring = str3.substring(2);
         System.out.println(substring);

         String substring1 = str3.substring(2, 3);
         System.out.println(substring1);

         String str5="aBhE";
         System.out.println(str5.toUpperCase());
         System.out.println(str5.toLowerCase());
         String str6=str5.concat(str3);
         System.out.println(str6);
     }
     @Test
     public void test03(){
         String string = "12345";
         int index = string.indexOf('1');
         System.out.println(index);

         index=string.indexOf('3',2);
         System.out.println(index);

         System.out.println(string.lastIndexOf(5,2));
     }
     @Test
     public void test04(){
         String str1 = "abc";
         String str2="ABC";

         System.out.println(str1.equals(str2));
         System.out.println(str1.equalsIgnoreCase(str2));

         System.out.println(str1.concat("a"));
         String str4="123456";
         System.out.println(str4.contains("45"));
         System.out.println(str4.startsWith("1"));
         System.out.println(str4.startsWith("1",1));
         System.out.println(str4.endsWith("5"));
        // StringBuffer stringBuffer = new StringBuffer("abced");
        // System.out.println(stringBuffer.insert(3, "f"));
         System.out.println(str4.isEmpty());
         //String str6=null;//NullPointerException
         //System.out.println(str6.isEmpty());
     }

     @Test
     public void test07(){
         String str1="123456";
         System.out.println(str1.length());

        // String intern = str1.intern();
         //System.out.println(intern==str1);
         char c = str1.charAt(2);
         System.out.println(c);
     }

     @Test
     public void test08(){
         char[] chars = {'1','2','3','5'};
         String str1=new String(chars);
         System.out.println(str1);

         String str2=new String(chars,0,chars.length);
         System.out.println(str2);

         String str = String.copyValueOf(chars);
         System.out.println(str);
         System.out.println(String.copyValueOf(chars, 1, 2));

         System.out.println(String.valueOf(chars));
         System.out.println(String.valueOf(chars, 1, 2));
     }

     @Test
     public void test09(){
         String str1="123456";
         char[] chars = str1.toCharArray();
         for (int i = 0; i < chars.length; i++) {
             System.out.println(chars[i]);
         }
     }
     @Test
     public void test10(){
         String string = "an dk jkj k123";
         String replace = string.replace("12", "45oo");
         System.out.println(replace);
         String[] s = string.split(" ");
         for (String string1 : s) {
             System.out.println(string1);
         }
     }
}
