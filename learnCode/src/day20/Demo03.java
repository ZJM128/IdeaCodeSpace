package day20;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 字符输入流
 */
public class Demo03 {
    @Test
    public void test01() throws IOException {
        File file=new File("D:\\IOtest\\143.txt");
        if(file.exists()) {
            System.out.println("进来");
            FileReader fileReader = new FileReader(file);
           // int i=fileReader.read();
            char[]chars=new char[10];
            int read = fileReader.read(chars,2,2);
            for(char c:chars){
                System.out.print(c+"-");
            }
            fileReader.close();
            //System.out.println(read);

            //System.out.println((char)i);
        }else{
            System.out.println("该文件不存在");
        }
    }
}
