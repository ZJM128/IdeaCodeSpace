package day19.review;

import org.junit.Test;

import java.io.File;

public class FileTestWork02 {
    @Test
    public void test01(){
        File file=new File("D:\\testIO");
        finaFile(file);
    }

    public void finaFile(File file){
        if(file!=null){
            if(file.isDirectory()){
                File[] files=file.listFiles();
                for(File file1 :files){
                    finaFile(file1);
                }
            }
            System.out.println(file.getName());
        }
    }
}
