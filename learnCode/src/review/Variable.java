package review;


import org.junit.Test;

/*
*@Description:
*@author:zhijm
*@Date:2020/6/4 21:11
*/
public class Variable {
    @Test
    public void test(){
        
        /*byte b=8;
        short s=9;
        int i=10;
        long l=10;

        float f=1.5f;
        double d=1.5;

        char c='2';

        boolean b1 = false;

        byte e=9;
        short s1=e;
       int i3=e;
        int i2=e+s1;*/

//        System.out.println(-1%5);
//        System.out.println(1%-5);
//
//        int a=11;
//        int b=10;
//        boolean bo=a==b;
//        System.out.println(bo);


        int a=10;
      //  boolean boo=a>10 && a++<20;//10
        boolean boo=a>10 & a++<20;//11
        System.out.println(a);
        int i=0;
        char c='w';
        short s=9;
        byte b=9;
        String s1 = "d";
        switch(i){
            case 1:
                System.out.println("s");
                break;
            case 2:
                System.out.println(1);
                break;
            default:
                break;
        }
    }

}
