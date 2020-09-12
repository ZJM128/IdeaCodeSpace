package com.atguigu.test;

import com.atguigu.HelloTest;

public class Person {
    public static void main(String[] args) {
       /* HelloTest helloTest=new HelloTest();
        HelloTest helloTest1=new HelloTest("李白",23);
        System.out.println(helloTest1);
        helloTest.test();*/
        new HelloTest().changeFile();
    }
    public void test01(){
      new HelloTest().changeFile();
    }
}
