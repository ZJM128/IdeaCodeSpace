package com.atguigu;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HelloTest {
    private  String name;
    private int age;

    public HelloTest() {
    }

    public HelloTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "HelloTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void test(){
        System.out.println("hello word");
    }
    public void changeFile(){
        try(FileInputStream fileInputStream = new FileInputStream("D:/chromeDownloads/5.jpg");
            FileOutputStream outputStream=new FileOutputStream("D:\\chromeDownloads\\6.gif")
        ){

            byte[]bytes=new byte[1024];
            int len=0;
            while((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            System.out.println("转换成功!!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
