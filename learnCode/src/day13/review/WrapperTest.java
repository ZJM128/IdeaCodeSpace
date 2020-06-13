package day13.review;

import org.junit.Test;

public class WrapperTest {

    @Test
    public void test09(){
        String string="123";
        Integer integer = Integer.valueOf(string);
        System.out.println(integer);
         String s="1.36";
        Double aDouble = Double.valueOf(s);
        System.out.println(aDouble);
    }
    @Test
    public void test08(){
        String str="123";
        int i = Integer.parseInt(str);
        System.out.println(i);

        String str1="1.234";
        double parseDouble = Double.parseDouble(str1);
        System.out.println(parseDouble);

        String str2="true";
        boolean b = Boolean.parseBoolean(str2);
        System.out.println(b);

        //
        String str4="c";
        Character character = str4.charAt(0);
        System.out.println(character);
    }

    @Test
    public void test07(){
        String str="1234";//NumberFormatException
        Integer integer=new Integer(str);
        System.out.println(integer);

        String str1="15.69f";
        Float f=new Float(str1);
        System.out.println(f);

        String str2="false";
        Boolean n=new Boolean(str2);
        System.out.println(n);


    }


    @Test
    public void test06(){
        /*int a=100;
        String string = a+"";
        System.out.println(string);*/

        int num=123;
        String s = Integer.toString(num);
        System.out.println(s);
        System.out.println("------------------------");
        float f=123.09f;
        String s1 = Float.toString(f);
        System.out.println(s1);

        boolean boo=false;
        String s2 = Boolean.toString(boo);
        System.out.println(s2);

        String s3 = String.valueOf(num);
        System.out.println(s3);
    }

    @Test
    public void test05(){
        /*Integer integer=123;
        Integer integer1=123;
        System.out.println(integer==integer1);// 缓存 -128~127

        Integer integer2=128;
        Integer integer3=128;
        System.out.println(integer2==integer3);*/

     /*   Integer integer4=new Integer(32);
        Integer integer5=new Integer(32);
        System.out.println(integer4==32);
        System.out.println(integer4==integer5);
        */

        Integer integer=new Integer(1269);
        System.out.println(integer==1269);
        /*Integer integer=1235;
        System.out.println(integer);
        int i=integer;
        System.out.println(i);*/
    }

    @Test
    public void test03(){
        int a=100;
        Integer integer=Integer.valueOf(a);
        System.out.println(integer);

        int intValue = integer.intValue();
        System.out.println(intValue);
        System.out.println("----------------");

        Character character=Character.valueOf('h');

        char charValue = character.charValue();
        System.out.println(character);

        Double bb=Double.valueOf(1.369);
        double b=bb.doubleValue();
        System.out.println(b);
    }

    @Test
    public void test02(){
        int a=100;
        Integer integer=Integer.valueOf(a);
        System.out.println(integer);

        System.out.println("---------------------");
        double d=1.09;
        Double double1=Double.valueOf(d);
        System.out.println(double1);

        System.out.println("===================");
        char c='c';
        Character character = Character.valueOf(c);
        System.out.println(character);

    }


    @Test
    public void test01(){
        /*int a=100;
        Integer integer=new Integer(a);
        System.out.println(integer);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Integer.toHexString(a));
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toOctalString(a));*/

        float f1=18.9f;
        Float f2=new Float(f1);
        System.out.println(f2);

        double d=1.36;
        Double d2=new Double(d);
        System.out.println(d2);

        boolean flag=false;
        Boolean b=new Boolean(flag);
        System.out.println(b);
    }
      
}
