package day19.review;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;

public class FileTest {
    public static void main(String[] args) {
        File file1 = new File("././learnCode/info.properties");
        System.out.println("相对路劲"+file1.exists());
    }
    @Test
    public void test06(){
        File file=new File("D:\\IOtest");
        String[] list = file.list();
        for (String string : list) {
            System.out.println(string);
        }

        System.out.println("===================");
        File[]files=file.listFiles();
        for (File file1 : files) {
            System.out.println(file1);
        }
        System.out.println("--------------------------");
        File[] files1=file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains(".txt");
            }
        });
        for (File file1 : files1) {
            System.out.println(file1);
        }
    }
    @Test
    public void test05(){
        File file=new File("D:\\IOtest\\66.txt");
        /*if(!file.exists()){
            try {
                file.createNewFile();// 如果目录不存在 会报:系统找不到指定的路径。异常
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        //file.delete();

        /*File file1=new File("D:\\IOtest\\test");
        if(!file1.exists()){
            file1.mkdir();
            System.out.println("目录创建成功");
        }*/

        File file1=new File("D:\\IOtest\\testio\\aa");
        //delete(file1);
       // System.out.println("删除成功");
        if(!file1.exists()){
            file1.mkdirs();
            System.out.println("创建成功");
        }

    }

    public void delete(File file){
        if(file!=null){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    delete(file1);
                }
            }
            file.delete();// 删除文件或空文件
        }
    }
    @Test
    public void test04(){
        File file =new File("D:\\IOtest");

        System.out.println("文件是否存在"+file.exists());
        System.out.println("file是否是目录"+file.isDirectory());//file不存在返回false
        System.out.println("file是否是文件"+file.isFile());

        System.out.println("file是否采用绝对路径"+file.isAbsolute());

        System.out.println("file是否可读"+file.canRead());
        System.out.println("file是否可以写"+file.canWrite());
        System.out.println("file是否隐藏"+file.isHidden());
    }
    @Test
    public void test03(){
        File file =new File("D:\\IOtest");
        File file1 = new File("././learnCode/info.properties");
        //System.out.println(file1.exists());
        System.out.println("获取路径:"+file.getPath());

        System.out.println("获取绝对路径"+file1.getAbsolutePath());

        try {
            System.out.println("获取规范路径"+file1.getCanonicalPath());
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test02(){
        File file=new File("D:/IOtest/12.txt");
        File file1=new File("D:/IOtest");

        System.out.println(file.exists());// 判断目录或文件是否存在
        System.out.println("文件内容的的大小"+file.length());// 文件里面内容的字节数
        System.out.println("目录的大小"+file1.length());// 目录的长度都是0

        System.out.println("路径:"+file.getPath());// File的对象怎么创建的 就怎么取
        System.out.println("路径2:"+file1.getPath());// File的对象怎么创建的 就怎么取

        System.out.println("最后修改时间:"+file.lastModified());// 返回的是毫秒值
        System.out.println("最后修改时间:"+new Date(file1.lastModified()));
    }
    @Test
    public void test01(){
        File file=new File("D:\\IOtest");
        System.out.println(file);// 不关乎你存不存在 有路径就行
        File parentPth=new File("D:");
        File file1=new File(parentPth,"IOtest");
        System.out.println(file1);
        File file2=new File("D:","IOtest");
        System.out.println(file2);
        System.out.println(file2.exists());
    }
}
