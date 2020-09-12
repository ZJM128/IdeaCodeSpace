package day19.homework;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Test01 {
    public static void main(String[] args) throws IOException {
      /*  File file = new File("D:\\testoi");
        if(!file.exists()){
            file.mkdir();
        }*/
        File file=new File("./learnCode/testio");
        if(!file.exists()){
            file.mkdir();
        }
        File file1=new File(file,"1.html");
        if(!file1.exists()){
            file1.createNewFile() ;
        }
    }
    @Test
    public void test02(){
        System.out.println("12");
    }
}
