package com.atguigu.jvm;

public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        int i=10;
        {
            int a=i+10;
        }
        int c=90;
        System.out.println(i);
    }
}
