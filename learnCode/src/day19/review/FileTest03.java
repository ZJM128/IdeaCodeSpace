package day19.review;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

public class FileTest03 {
    /**
     *
     */
    @Test
    public void test02(){
        File file=new File("D:\\IOtest");
        //finaFile(file);
        finalFile(file);
    }

    /**
     * 列出"D:/atguigu"下所有".java"文件
     * @param file
     */
    public void finaFile(File file){
        if(file!=null){
            if(file.isDirectory()){
                File[] files=file.listFiles();
                for (File file1 : files) {
                    finaFile(file1);
                }
            }

            if(file.getName().contains(".java")) {
                System.out.println(file.getName());
            }
        }
    }

    public void finalFile(File file){
        if(file!=null){
            File[] files=file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().contains(".java") || pathname.isDirectory();
                }
            });
            if(files!=null){
                for (File file1 : files) {
                    if(file1.isFile()){
                        System.out.println(file1);
                    }

                    finalFile(file1);
                }
            }
        }
    }
    @Test
    public void test03(){
        File file=new File("D:\\IOtest");
        System.out.println(count(file));
    }
    /**
     * 递归求目录总大小
     */
    public long count(File file){
        if(file!=null){
            // 返回文件夹的大小
            if(file.isDirectory()){
                File[] files = file.listFiles();
                long count=0;
                for (File file1 : files) {
                    count+=count(file1);
                }
                return count;
            }

            // 返回文件的大小
            return file.length();
        }

        // 不是目录不是文件则返回0
        return 0;
    }

}
