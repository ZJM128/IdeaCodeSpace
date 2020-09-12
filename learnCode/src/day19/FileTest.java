package day19;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
*@Description:File有关
*@author:zhijm
*@Date:2020/6/18 11:04
*/
public class FileTest {

    public static void main(String[] args) {
        // 直接输入路径string
        File file=new File("D:\\testIO");
        File file1=new File("D:\\testIO\\12.txt");// 绝对路径
        File file2=new File("./learnCode/info.properties"); // 相对路径

        // 有一个根路径的File对象,文件路径的字符串
            File file3= new File(file,"12.txt");
            // 根路径的字符串,文件路径的字符串
            File file4=new File("D\\testIO","12.txt");

            if(file1.exists()){
                System.out.println("文件存在");
        }

    }
    @Test
    public void test01(){
        // 绝对路径
        File file=new File("D:\\testIO\\12.txt");
        if(file.exists()){
            System.out.println("文件1存在");
        }
        // 相对路径
        File file1=new File("./learnCode/info.properties");
        if(file1.exists()){
            System.out.println("文件2存在");
        }

        // parent 路径下是否有文件xxx
        File file2=new File("D:\\testIO");
        File file3=new File(file2,"12.txt");
        if(file3.exists()){
            System.out.println("文件3存在");
        }
        // 文件夹下是否有xx文件
        String parentPath="D:\\testIO";
        String childPath="12.txt";
        File file4=new File(parentPath,childPath);
        if(file4.exists()){
            System.out.println("文件4存在");
        }
    }
    @Test
    public void test02(){
        File file=new File("D:\\testIO\\12.txt");
        System.out.println("文件的名称:"+file.getName());

        System.out.println("文件的长度:"+file.length());

        System.out.println("文件的路径:"+file.getPath());

        System.out.println("最后修改时间"+file.lastModified());

    }
    @Test
    public void test03(){
        File file=new File("D:\\testIO\\12.txt");
        File file1=new File("./learnCode/info.properties");
        System.out.println("获取文件的路径"+file.getPath());

        // 绝对路径
        System.out.println("获取绝对路径"+file1.getAbsolutePath());

        try {
            // 获取规范的路径
            System.out.println("获取规范路径"+file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test04(){
        File file=new File("D:\\testIO\\12.txt");
        /*System.out.println("文件是否存在："+file.exists());
        System.out.println("file是否是目录："+file.isDirectory());
        System.out.println("file是否是文件："+file.isFile());
        System.out.println("file是否是采用绝对路径："+file.isAbsolute());
        System.out.println("file是否可读："+file.canRead());
        System.out.println("file是否可写："+file.canWrite());
        System.out.println("file是否隐藏："+file.isHidden());*/
        System.out.println("文件是否存在"+file.exists());
        System.out.println("file是否是目录"+file.isDirectory());// 如果文件目录不存在返回false
        System.out.println("file是否是文件"+file.isFile());// 如果文件不存在 返回false
        System.out.println("file是是否是采用绝对路径"+file.isAbsolute());
        System.out.println("file是否可读"+file.canRead());
        System.out.println("file是否可写"+file.canWrite());
        System.out.println("file是否隐藏"+file.isHidden());
    }

    /**
     * 创建和删除文件
     * createNewFile() 新建问文件      如果文件所在的目录不存在会有异常产生
     * mkdir 新建一级目录 如果是多级目录 则一级也不能创建
     * mkdirs() 可以创建一级或多级的目录
     * delete 删除空的文件
     */
    @Test
    public void test05(){
        // 创建文件
      /*  File file=new File("D:/testIO/13.txt");
        if(!file.exists()){
            try {
                //新建文件[新建不了目录](如果此文件的目录是不存在的，会报错)
                //java.io.IOException: 系统找不到指定的路径。
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        // 创建一级目录

    /*File file=new File("D:/testIO2");
        if(!file.exists()){
            file.mkdir();
            System.out.println("创建成功");
        }else{
            // 如果目录存在默认也是不创建的
            file.mkdir();
            System.out.println("文件已存在,能不能创建");//
        }*/

        // 创建多级目录
       /* File file=new File("D:/testIO3/aa/bb/cc/hh");
        if(!file.exists()){
            //file.mkdirs();//此方法创建的就是目录 ,无论目录的名称是否是都是文件名
            try {
                file.createNewFile();// 此方法创建的就是一个文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("创建成功");
        }else{
            System.out.println("已经存在了");
        }*/

       // 删除 只能空的目录或文件
        /*File file=new File("D:/testIO2");
        if(file.exists()){
            file.delete();
            System.out.println("删除成功");
        }*/

        
    }

    /**
     *  1. 将我刚刚将的构造器和常用方法  敲一遍
     *         2. 练习题：
     *             已知File file=new File("d:/test/mysql.txt");
     *             去判断文件是否存在，如果存在就打印其相关信息(名称、路径、大小、修改时间...)
     *             如果不存在就新建出来
     */
    @Test
    public void test(){
        File file=new File("d:/testIO/mysql.txt");
        // 使用字符串知识
        int index = file.getPath().lastIndexOf("\\");
        String substring = file.getPath().substring(index);
        System.out.println(substring);
        if(!file.exists()) {
            if (substring.contains(".")) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.out.println("名称:"+file.getName());
            System.out.println("路径:"+file.getPath());
            System.out.println("大小:"+file.length());
            System.out.println("修改时间:"+file.lastModified());
        }
    }

    /**
     * 练习题2：
     *             已知File file=new File("d:/test/mysql.txt");
     *             文件和文件所在的目录是否存在，都不清楚(mysql.txt和test目录是否存在都不知道)
     *             最后我需要的结果是d盘下肯定有test目录，test目录下肯定有mysql.txt文件
     *                 用getParent()做一次
     *                 用getParentFile()在做一次
     */
    @Test
    public void test06(){
       /* File file =new File("d:/test/mysql.txt");
        if(!file.exists()){
            String parent = file.getParent();
            File file1=new File(parent);
            if(!file1.exists()){
                file1.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        File file =new File("d:/test1/mysql.txt");
        if(!file.exists()){
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("创建完成");
        }
    }

    /**
     * 递归删除
     */
    @Test
    public void test07(){
        File file=new File("D:\\testIO3");
        deleteFile(file);
        System.out.println("删除成功");
    }

    /**
     * 练习题3：
     *    已知目录File file=new File("d:/iotest");要删除该目录
     *    但是不知道此目录内是否有内容！  delete
     *    1. 先将file清空(会在次遇到文件夹[1.清空2.delete])  2. 在执行delete方法
     */
    public void deleteFile(File file){
        if(file!=null){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    deleteFile(file1);
                }
            }
            file.delete();
        }
    }
    @Test
    public void test08(){
        // listFiles() list()方法只能用于目录中 如果是文件则包 NullPointerException异常
        File file=new File("D:\\test1");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1);
        }
    }

    /**
     * 递归删除
     */
    @Test
    public void test09(){
        File file=new File("D:\\test1");
        delete(file);
        System.out.println("删除成功");
    }

    public  void delete(File file){
        if(file!=null){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    delete(file1);
                }
            }
            file.delete();
        }
    }
}
