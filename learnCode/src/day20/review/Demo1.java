package day20.review;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo1 {
    @Test
    public void test01(){
        InputStream inputStream =null;
        try {
            inputStream= new FileInputStream("D:\\IOtest\\mysql1.txt");
            byte[]bytes=new byte[12];
            int len=0;
            len=inputStream.read(bytes,2,6);
            for(byte b:bytes){
                System.out.print(b+"---");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
