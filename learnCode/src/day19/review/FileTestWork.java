package day19.review;

import org.junit.Test;

import java.io.File;

public class FileTestWork {
    @Test
    public void test01(){
        File file=new File("D:\\testIO");
        if(!file.exists()){
            file.mkdir();
            System.out.println("目录创建成功");
        }

        File file1=new File(file,"1.txt");
        if(!file1.exists()){
            try{
                file1.createNewFile();
            }catch(Exception e){
                System.out.println(   e.getMessage());
                e.printStackTrace();
            }
            System.out.println("文件创建成功");
        }
    }
}
