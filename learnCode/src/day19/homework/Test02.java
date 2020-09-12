package day19.homework;

import java.io.File;
import java.io.IOException;

public class Test02 {
    public static void main(String[] args) throws IOException {
        /*File file=new File("D:\\testoi\\a.txt");
        if(!file.exists()){
            file.createNewFile();
        }*/
        /*File file=new File("D:\\testoi\\a.txt");
        if(file.exists()){
            System.out.println(file.getName());
            System.out.println(file.length());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getParent());
        }*/

        /*File file=new File("D:\\testoi");
        if(file.isDirectory()){
            System.out.println(file.getName()+"是一个目录");
        }else if(file.isFile()){
            System.out.println(file.getName()+"是一个文件");
        }*/

        /*File file=new File("D:\\\\testoi\\\\a.txt");
        if(file.exists()){
            file.delete();
        }*/

        File file =new File("./learnCode/testio/1.html");
        if(file.exists()){
            File parentFile = file.getParentFile();
            file.delete();
            System.out.println("删除文件成功");
            parentFile.delete();
            System.out.println("删除目录成功");
        }
    }
}
